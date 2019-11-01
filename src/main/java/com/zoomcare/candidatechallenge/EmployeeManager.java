package com.zoomcare.candidatechallenge;

import com.zoomcare.candidatechallenge.daos.IEmployeeDao;
import com.zoomcare.candidatechallenge.entities.Employee;
import com.zoomcare.candidatechallenge.errorhandling.EmployeeRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * This implements the contract defined in the {@link IEmployeeManager} interface.
 *
 * @author Allen Wickham
 */
@Component
public class EmployeeManager implements IEmployeeManager
{
    private IEmployeeDao employeeDao;

    public IEmployeeDao getEmployeeDao()
    {
        return employeeDao;
    }

    @Autowired
    public void setEmployeeDao(IEmployeeDao employeeDao)
    {
        this.employeeDao = employeeDao;
    }


    @Override
    public List<Employee> getAllEmployees()
    {
        return getEmployeeDao().findAll();
    }

    @Override
    public Employee getEmployee(Long employeeId)
    {
        Optional<Employee> possibleEmployee = getEmployeeDao().findById(employeeId);

        if (possibleEmployee.isPresent())
        {
            return possibleEmployee.get();
        }
        else
        {
            throw new EmployeeRuntimeException("Unable to locate the employee with the provided ID.", 404);
        }
    }
}
