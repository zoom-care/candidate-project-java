package com.zoomcare.candidatechallenge.expose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoomcare.candidatechallenge.business.impl.EmployeeServiceImpl;
import com.zoomcare.candidatechallenge.db.model.Employee;
import com.zoomcare.candidatechallenge.db.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {EmployeeController.class})
@AutoConfigureWebTestClient(timeout = "120000")
@Import({
    EmployeeServiceImpl.class,
})
public class EmployeeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private EmployeeRepository employeeRepository;

  @Before
  public void setUp() {
    when(employeeRepository
        .findAll())
        .thenReturn(Optional
            .of(
                Arrays.asList(
                    Employee.builder()
                        .employeeId(1L)
                        .region("Europe")
                        .title("CEO")
                        .build(),
                    Employee.builder()
                        .employeeId(5L)
                        .supervisorId(1L)
                        .region("Europe")
                        .title("Regional Director of Sales")
                        .build()
                )
            ));

    when(employeeRepository.findByEmployeeId(any()))
        .thenReturn(Optional.of(
            Employee.builder()
                .employeeId(4L)
                .supervisorId(3L)
                .region("North America")
                .title("Sales Representative")
                .build()
        ));
  }

  @Test
  public void should_return_all_employees() throws Exception {
    mockMvc.perform( MockMvcRequestBuilders
            .get("/v1/employees")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void should_not_return_employees() throws Exception {

    when(employeeRepository
        .findAll())
        .thenReturn(Optional.empty());

    mockMvc.perform( MockMvcRequestBuilders
            .get("/v1/employees")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  public void should_return_internal_server_error() throws Exception {

    when(employeeRepository
        .findAll())
        .thenThrow(new RuntimeException());

    mockMvc.perform( MockMvcRequestBuilders
            .get("/v1/employees")
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isInternalServerError());
  }

  @Test
  public void should_return_employee_id_4() throws Exception {
    ResultActions resultActions = mockMvc.perform( MockMvcRequestBuilders
            .get("/v1/employees/{employeeId}",4)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    Employee response = objectMapper.readValue(contentAsString, Employee.class);

    assertThat("Wrong employeeId",response.getEmployeeId(),is(4L));
    assertThat("Wrong supervisorId",response.getSupervisorId(),is(3L));
    assertThat("Wrong region",response.getRegion(),is("North America"));
    assertThat("Wrong title",response.getTitle(),is("Sales Representative"));
  }

  @Test
  public void should_not_found_employee_id() throws Exception {
    when(employeeRepository.findByEmployeeId(any()))
        .thenReturn(Optional.empty());

    mockMvc.perform( MockMvcRequestBuilders
            .get("/v1/employees/{employeeId}",4)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  public void should_return_internal_server_error_for_employee_id() throws Exception {
    when(employeeRepository.findByEmployeeId(any()))
        .thenThrow(new RuntimeException());

    mockMvc.perform( MockMvcRequestBuilders
            .get("/v1/employees/{employeeId}",4)
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isInternalServerError());
  }

}
