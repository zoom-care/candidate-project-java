package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.model.EmployeeResponse;
import com.zoomcare.candidatechallenge.model.Property;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;
import com.zoomcare.candidatechallenge.repository.PropertyRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EmployeeServiceImplTest {

    private EmployeeServiceImpl service;
    EmployeeRepository employeeRepository;
    PropertyRepository propertyRepository;
    private static final String TITLE = "title";
    private static final String REGION = "region";

    @Before
    public void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        propertyRepository = Mockito.mock(PropertyRepository.class);
        service = new EmployeeServiceImpl(employeeRepository, propertyRepository);
    }

    @Test
    public void test_getEmployee_no_subordinate_success() {
        Long employeeId = 5L;
        Optional<Employee> employeeOptional = Optional.of(getEmployee(employeeId, 2L));
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put(REGION, "Europe");
        propertyMap.put(TITLE, "Regional Director of Sales");
        List<Property> propertyList = getPropertyList(employeeId, propertyMap);
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(employeeOptional);
        Mockito.when(employeeRepository.findAllBySupervisorId(Mockito.any())).thenReturn(new ArrayList<>());
        Mockito.when(propertyRepository.findAllPropertyByEmployeeId(Mockito.any())).thenReturn(propertyList);
        Optional<EmployeeResponse> employee = service.getEmployee(employeeId);
        assertNotNull(employee);
        assertTrue(employee.get().getReporterList().isEmpty());
        assertFalse(employee.get().getPropertyList().isEmpty());
    }

    @Test
    public void test_getEmployee_with_subordinate_success() {
        Long employeeId = 3L;
        Optional<Employee> employeeOptional = Optional.of(getEmployee(employeeId, 2L));
        List<Employee> reporterList = getReporterList(employeeId);
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put(REGION, "North America");
        propertyMap.put(TITLE, "Regional Director of Sales");
        List<Property> propertyList = getPropertyList(employeeId, propertyMap);
        Mockito.when(employeeRepository.findById(Mockito.any())).thenReturn(employeeOptional);
        Mockito.when(employeeRepository.findAllBySupervisorId(Mockito.any())).thenReturn(reporterList);
        Mockito.when(propertyRepository.findAllPropertyByEmployeeId(Mockito.any())).thenReturn(propertyList);
        Optional<EmployeeResponse> employee = service.getEmployee(employeeId);
        assertNotNull(employee);
        assertFalse(employee.get().getReporterList().isEmpty());
        assertFalse(employee.get().getPropertyList().isEmpty());
    }

    @Test
    public void test_getEmployees_success() {
        List<Employee> employeeList = getAllEmployee();
        List<Property> propertyList = getAllProperty();
        Set<Long> supervisorIds = getSupervisorIdList();
        List<Employee> employeeBySupervisorIdList = getAllEmployeeBySupervisorId(supervisorIds);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        Mockito.when(employeeRepository.findAllById(supervisorIds)).thenReturn(employeeBySupervisorIdList);
        Mockito.when(propertyRepository.findAll()).thenReturn(propertyList);
        Mockito.when(employeeRepository.findAllBySupervisorId(Mockito.any())).thenReturn(new ArrayList<>());
        List<EmployeeResponse> employeeResponseList = service.getEmployees();
        assertFalse(employeeResponseList.get(0).getPropertyList().isEmpty());
        assertEquals(4, employeeResponseList.size());
        assertNotNull(employeeResponseList);
    }

    private List<Employee> getAllEmployeeBySupervisorId(Set<Long> supervisorIds) {
        List<Employee> employeesBySupervisorId = new ArrayList<>();
        for (Long id : supervisorIds) {
            Employee employee = new Employee();
            employee.setId(id);
            Long supervisorId = null;
            if (id == 2 || id == 7) supervisorId = 1L;
            if (id == 3) supervisorId = 2L;
            employee.setSupervisorId(supervisorId);
            employeesBySupervisorId.add(employee);
        }
        return employeesBySupervisorId;
    }

    private List<Property> getAllProperty() {
        List<Property> propertyList = new ArrayList<>();
        Property property1 = new Property();
        property1.setEmployeeId(1L);
        property1.setKey(TITLE);
        property1.setValue("CEO");
        propertyList.add(property1);
        Property property2 = new Property();
        property2.setEmployeeId(2L);
        property2.setKey(TITLE);
        property2.setValue("Vice President of Sales");
        propertyList.add(property2);
        Property property3 = new Property();
        property3.setEmployeeId(3L);
        property3.setKey(TITLE);
        property3.setValue("Regional Director of Sales");
        propertyList.add(property3);
        Property property4 = new Property();
        property4.setEmployeeId(3L);
        property4.setKey(REGION);
        property4.setValue("North America");
        propertyList.add(property4);
        Property property5 = new Property();
        property5.setEmployeeId(4L);
        property5.setKey(TITLE);
        property5.setValue("Sales Representative");
        propertyList.add(property5);
        Property property6 = new Property();
        property6.setEmployeeId(5L);
        property6.setKey(TITLE);
        property6.setValue("Regional Director of Sales");
        propertyList.add(property6);
        Property property7 = new Property();
        property7.setEmployeeId(5L);
        property7.setKey(REGION);
        property7.setValue("Europe");
        propertyList.add(property7);
        Property property8 = new Property();
        property8.setEmployeeId(6L);
        property8.setKey(TITLE);
        property8.setValue("Vice President of People");
        propertyList.add(property8);
        Property property9 = new Property();
        property9.setEmployeeId(7L);
        property9.setKey(TITLE);
        property9.setValue("Vice President of Marketing");
        propertyList.add(property9);
        Property property10 = new Property();
        property10.setEmployeeId(8L);
        property10.setKey(TITLE);
        property10.setValue("Regional Director of Marketing");
        propertyList.add(property10);
        Property property11 = new Property();
        property11.setEmployeeId(8L);
        property11.setKey(REGION);
        property11.setValue("North America");
        propertyList.add(property11);
        Property property12 = new Property();
        property12.setEmployeeId(9L);
        property12.setKey(TITLE);
        property12.setValue("Regional Director of Marketing");
        propertyList.add(property12);
        Property property13 = new Property();
        property13.setEmployeeId(9L);
        property13.setKey(REGION);
        property13.setValue("Europe");
        propertyList.add(property13);
        return propertyList;
    }

    private List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Employee employee = new Employee();
            employee.setId(Long.valueOf(i));
            if (i > 1 && i < 5) {
                employee.setSupervisorId(Long.valueOf(i - 1));
            } else if (i == 5) {
                employee.setSupervisorId(Long.valueOf(i - 3));
            } else if (i > 5 && i < 8) {
                employee.setSupervisorId(1L);
            } else if (i > 7) {
                employee.setSupervisorId(7L);
            }
            employeeList.add(employee);
        }
        return employeeList;
    }

    private Set<Long> getSupervisorIdList() {
        Set<Long> supervisorIds = new HashSet<>();
        supervisorIds.add(1L);
        supervisorIds.add(2L);
        supervisorIds.add(3L);
        supervisorIds.add(7L);
        return supervisorIds;
    }

    private Employee getEmployee(Long id, Long supervisorId) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setSupervisorId(supervisorId);
        return employee;
    }

    private List<Employee> getReporterList(Long supervisorId) {
        List<Employee> reporterList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId(4L);
        employee.setSupervisorId(supervisorId);
        reporterList.add(employee);
        return reporterList;
    }

    private List<Property> getPropertyList(Long employeeId, Map<String, String> propertyMap) {
        List<Property> propertyList = new ArrayList<>();
        for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
            Property property = new Property();
            property.setEmployeeId(employeeId);
            property.setKey(entry.getKey());
            property.setValue(entry.getValue());
            propertyList.add(property);
        }
        return propertyList;
    }

}