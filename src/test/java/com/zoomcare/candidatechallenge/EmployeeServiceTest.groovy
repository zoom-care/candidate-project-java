package com.zoomcare.candidatechallenge

import com.zoomcare.candidatechallenge.dtos.UiEmployee
import com.zoomcare.candidatechallenge.entities.Employee
import com.zoomcare.candidatechallenge.entities.EmployeeProperty
import com.zoomcare.candidatechallenge.errorhandling.EmployeeRuntimeException
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mock
import org.springframework.http.ResponseEntity

import static org.mockito.ArgumentMatchers.anyLong
import static org.mockito.Mockito.doReturn
import static org.mockito.Mockito.doThrow
import static org.mockito.MockitoAnnotations.initMocks

class EmployeeServiceTest extends GroovyTestCase
{
    /**
     * Defines a rule that an expected exception will be thrown during a test.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * The mock instance of the {@link IEmployeeManager} class.
     */
    @Mock
    private IEmployeeManager employeeManager;

    /**
     * The instance of the class under test.
     */
    private EmployeeService employeeService;

    /**
     * Initializes the mocks and creates a new instance of the class under test prior to each test.
     */
    @Before
    public void setUp()
    {
        initMocks(this);

        employeeService = new EmployeeService();
        employeeService.setEmployeeManager(employeeManager);
    }

    /**
     * Tests the functionality of the {@link EmployeeService#getAllEmployeeInformation()} method.
     */
    @Test
    void testGetAllEmployeeInformation()
    {
        // No employees available

        doReturn(Collections.emptyList())
                .when(employeeManager).getAllEmployees();

        ResponseEntity<List<UiEmployee>> uiEmployees = employeeService.getAllEmployeeInformation();

        Assert.assertTrue(uiEmployees.getBody().isEmpty());

        // Employee with no supervisor or properties

        final Employee employee = new Employee();

        employee.setId(10);

        doReturn(Collections.singletonList(employee))
                .when(employeeManager).getAllEmployees();

        uiEmployees = employeeService.getAllEmployeeInformation();
        UiEmployee uiEmployee = uiEmployees.getBody().get(0);

        Assert.assertEquals(employee.getId(), uiEmployee.getEmployeeId());
        Assert.assertNull(uiEmployee.getEmployeeDetails());
        Assert.assertNull(uiEmployee.getSupervisor());

        // Employee with a supervisor (no properties), no employee properties

        final Employee supervisor = new Employee();

        employee.setId(12);
        employee.setSupervisor(supervisor);

        uiEmployees = employeeService.getAllEmployeeInformation();
        uiEmployee = uiEmployees.getBody().get(0);

        Assert.assertEquals(employee.getId(), uiEmployee.getEmployeeId());
        Assert.assertNull(uiEmployee.getEmployeeDetails());
        Assert.assertNotNull(uiEmployee.getSupervisor());
        Assert.assertEquals(supervisor.getId(), uiEmployee.getSupervisor().getEmployeeId());

        // Employee with a supervisor (no properties), one employee property

        final EmployeeProperty employeeProperty = new EmployeeProperty();

        employeeProperty.setEmployeeId(employee.getId());
        employeeProperty.setKey("test1");
        employeeProperty.setValue("test");

        employee.setEmployeeProperties(Collections.singletonList(employeeProperty));

        uiEmployees = employeeService.getAllEmployeeInformation();
        uiEmployee = uiEmployees.getBody().get(0);

        Assert.assertEquals(employee.getId(), uiEmployee.getEmployeeId());
        Assert.assertNotNull(uiEmployee.getEmployeeDetails());
        Assert.assertNotNull(uiEmployee.getSupervisor());
        Assert.assertEquals(employeeProperty.getKey(), uiEmployee.getEmployeeDetails().get(0).getKey());
        Assert.assertEquals(employeeProperty.getValue(), uiEmployee.getEmployeeDetails().get(0).getValue());
    }

    /**
     * Tests the functionality of the {@link EmployeeService#getEmployeeInformation(java.lang.Long)} method.
     */
    @Test
    void testGetEmployeeInformationWithEmployeeException()
    {
        doThrow(new EmployeeRuntimeException("Unable to locate the employee with the provided ID.", 404))
                .when(employeeManager).getEmployee(anyLong());

        expectedException.expect(EmployeeRuntimeException.class);
        expectedException.expectMessage("Unable to locate the employee with the provided ID.");

        employeeService.getEmployeeInformation(20);
    }

    void testConvertEmployee()
    {
        // TODO: add tests
    }
}
