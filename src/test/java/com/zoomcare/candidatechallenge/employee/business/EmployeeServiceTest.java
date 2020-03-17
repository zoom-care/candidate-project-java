package com.zoomcare.candidatechallenge.employee.business;

import com.zoomcare.candidatechallenge.employee.dataaccess.EmployeeDAO;
import com.zoomcare.candidatechallenge.employee.dataaccess.EmployeeRepository;
import com.zoomcare.candidatechallenge.employee.dataaccess.PropertyDAO;
import com.zoomcare.candidatechallenge.employee.dataaccess.PropertyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepositoryMock;

    @Mock
    PropertyRepository propertyRepositoryMock;

    private List<EmployeeDAO> buildTestEmployeeList(Long parentId) {
        int numberToCreate = ThreadLocalRandom.current().nextInt(0, 2);
        return buildTestEmployeeList(parentId, numberToCreate);
    }

    private List<EmployeeDAO> buildTestEmployeeList(Long parentId, int numberToCreate) {
        List<EmployeeDAO> results = new ArrayList<>();
        for (int currentItemCreating = 0; currentItemCreating < numberToCreate; currentItemCreating++) {
            results.add(buildEmployeeData(parentId));
        }
        return results;
    }

    private EmployeeDAO buildEmployeeData(Long parentId) {
        long id = ThreadLocalRandom.current().nextLong();
        return buildEmployeeData(parentId, id);
    }

    private EmployeeDAO buildEmployeeData(Long parentId, long id) {
        return EmployeeDAO.builder()
                .id(id)
                .supervisorId(parentId)
                .build();
    }

    private List<PropertyDAO> buildEmployeeProperty(Long employeeId) {
        int propertyCount = ThreadLocalRandom.current().nextInt(1, 2);
        List<PropertyDAO> results = new ArrayList<>();
        for (int currentProperty = 1; currentProperty <= propertyCount; currentProperty++) {
            results.add(PropertyDAO.builder()
                    .employeeId(employeeId)
                    .key("-property-key-" + currentProperty + "-employee-" + employeeId + "-")
                    .value("-property-value-" + currentProperty + "-employee-" + employeeId + "-")
                    .build());
        }
        return results;
    }


    @Test
    public void getEmployees() {
        when(employeeRepositoryMock.getAllBySupervisorIdNull()).thenReturn(buildTestEmployeeList(null, 3));
        when(employeeRepositoryMock.getAllBySupervisorId(anyLong())).then(x -> buildTestEmployeeList(x.getArgument(0)));
        when(propertyRepositoryMock.getAllByEmployeeId(anyLong())).then(x -> buildEmployeeProperty(x.getArgument(0)));

        EmployeeService employeeService = new EmployeeService(employeeRepositoryMock, propertyRepositoryMock);
        List<Employee> results = employeeService.getEmployees();
        Assert.assertNotEquals(0L, results.size());
        for (Employee employee :results) {
            if(employee.getDirectReports().size() > 0){
                for (Employee report : employee.getDirectReports()) {
                    Assert.assertEquals(employee.getId(), report.getSupervisorId());
                }
            }
            if(employee.getProperties().size() > 0){
                for(Property property : employee.getProperties()){
                    Assert.assertEquals(employee.getId(), property.getEmployeeId());
                }
            }

        }

    }


    @Test
    public void getEmployee() {
        when(employeeRepositoryMock.getById(anyLong())).then(x -> Optional.of(buildEmployeeData(2L, x.getArgument(0))));
        when(employeeRepositoryMock.getAllBySupervisorId(anyLong())).then(x -> buildTestEmployeeList(x.getArgument(0)));
        when(propertyRepositoryMock.getAllByEmployeeId(anyLong())).then(x -> buildEmployeeProperty(x.getArgument(0)));
        EmployeeService employeeService = new EmployeeService(employeeRepositoryMock, propertyRepositoryMock);
        Optional<Employee> result = employeeService.getEmployee(156L);
        Long expectedId = 156L;
        Employee employeeResult = result.orElseThrow(RuntimeException::new);
        Assert.assertEquals(expectedId, employeeResult.getId());
        if(employeeResult.getDirectReports().size() > 0){
            for (Employee report : employeeResult.getDirectReports()) {
                Assert.assertEquals(expectedId, report.getSupervisorId());
            }
        }
        if(employeeResult.getProperties().size() > 0){
            for(Property property : employeeResult.getProperties()){
                Assert.assertEquals(expectedId, property.getEmployeeId());
            }
        }
    }
}