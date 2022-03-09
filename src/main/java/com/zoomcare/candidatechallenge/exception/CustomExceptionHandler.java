package com.zoomcare.candidatechallenge.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CustomExceptionHandler class
 * @author aquintero
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<ErrorResponse> handleApiException(final HttpStatusCodeException exception)
    {
        log.error(exception.getMessage(), exception);
        final ErrorResponse error = new ErrorResponse(exception.getStatusCode().value(),
                exception.getStatusText(),
                new ArrayList<>());
        return new ResponseEntity<>(error, exception.getStatusCode());
    }


    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleHttpMessageConversionException(
            final HttpMessageConversionException exception)
    {
        log.error(exception.getMessage(), exception);
        final String message = Objects.isNull(exception.getCause().getCause())
                ? exception.getCause().getMessage() : exception.getCause().getCause().getMessage();
        final ErrorResponse error = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                message,
                new ArrayList<>());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorResponse> handleValidationException(final Exception exception)
    {
        log.error(exception.getMessage(), exception);
        final String message = Objects.isNull(exception.getCause().getCause())
                ? exception.getCause().getMessage() : exception.getCause().getCause().getMessage();
        final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message,
                new ArrayList<>());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(
            final MethodArgumentTypeMismatchException except)
    {
        final List<ErrorBody> errors = new ArrayList<>();
        errors.add(new ErrorBody(except.getName(),
                new ArrayList<>(Arrays.asList(ErrorMessages.ERROR_FIELD_FORMAT_MESSAGE))));
        log.error(except.getMessage(), except);
        final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ErrorResponse> handleConstraintViolation(final ConstraintViolationException exception)
    {
        log.error(exception.getMessage(), exception);
        final Map<String, List<String>> mapFields = new HashMap<>();
        final List<ErrorBody> details = new ArrayList<>();

        exception.getConstraintViolations().forEach(constViol -> {
            final String []field = constViol.getPropertyPath().toString().split("\\.");
            mapFields.computeIfAbsent(field[field.length - 1], key -> new ArrayList<>()).add(constViol.getMessage());
        });

        mapFields.forEach((key,value) -> details.add(new ErrorBody(key,value)));
        final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({ SQLException.class,
                        SystemGeneralException.class,
                        Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<ErrorResponse> handleAnyException(final Exception exception)
    {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getLocalizedMessage(),
                new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request)
    {
        log.error(exception.getMessage(), exception);
        final List<ErrorBody> details = new ArrayList<>();
        final Map<String, List<String>> listMapFields = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));
        listMapFields.forEach((key,value) -> details.add(new ErrorBody(key,value)));
        final Map<String, List<String>> listMapObjectName = exception.getBindingResult().getGlobalErrors().stream()
                .collect(Collectors.groupingBy(
                        ObjectError::getObjectName,
                        Collectors.mapping(ObjectError::getDefaultMessage, Collectors.toList())
                ));
        listMapObjectName.forEach((key,value) -> details.add(new ErrorBody(key,value)));
        final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException except,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request)
    {
        log.error(except.getMessage(), except);
        final ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), new ArrayList<>());
        String field;
        if (except.getCause() instanceof JsonParseException) {
            final JsonParseException exception = (JsonParseException) except.getCause();
            field = exception.getProcessor().getCurrentName();

        } else if (except.getCause() instanceof InvalidFormatException) {
            final InvalidFormatException invalidFormatException = (InvalidFormatException) except.getCause();
            field = invalidFormatException.getPath().get(invalidFormatException.getPath().size() - 1).getFieldName();

        } else {
            field = "";
        }
        final List<ErrorBody> errors = new ArrayList<>();
        errors.add(new ErrorBody(field,
                new ArrayList<>(Collections.singletonList(ErrorMessages.ERROR_FIELD_FORMAT_MESSAGE))));
        error.setErrors(errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
