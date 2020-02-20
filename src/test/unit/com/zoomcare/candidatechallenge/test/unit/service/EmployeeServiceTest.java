package com.zoomcare.candidatechallenge.test.unit.service;

import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @TestConfiguration
    static class EmployeeServiceTestContextConfiguration {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void getEmployeeById() {
        this.mockFindById();

        Employee employee = this.employeeService.getEmployee(1L);

        assertEquals(new Long(1L), employee.getId());
        assertEquals("CEO", employee.getProperties().get("title"));
    }

    private void mockFindById() {
        Map<String, String> properties = new HashMap<>();
        properties.put("title", "CEO");
        Employee test = new Employee(1L, properties, null);

        Mockito.when(employeeRepository.findById(1L))
                .thenReturn(Optional.ofNullable(test));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployeeByIdNotFound() {
        this.employeeService.getEmployee(1L);
    }

    @Test
    public void getTopLevel() {
        this.mockFindBySupervisorIdIsNull();

        List<Employee> employees = this.employeeService.getTopLevel();

        assertEquals(1, employees.size());
        assertEquals("CEO", employees.get(0).getProperties().get("title"));
        assertEquals("Vice President of Sales", employees.get(0).getDirectReports().get(0).getProperties().get("title"));
    }

    private void mockFindBySupervisorIdIsNull() {
        Map<String, String> ceoProperties = new HashMap<>();
        ceoProperties.put("title", "CEO");
        Employee ceo = new Employee(1L, ceoProperties, null);

        Map<String, String> vpsProperties = new HashMap<>();
        vpsProperties.put("title", "Vice President of Sales");
        Employee vps = new Employee(2L, vpsProperties, 1L);

        ceo.addDirectReports(vps);

        Mockito.when(employeeRepository.findBySupervisorIdIsNull())
                .thenReturn(new ArrayList<Employee>(Arrays.asList(ceo)));
    }
}
