package com.zoomcare.candidatechallenge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoomcare.candidatechallenge.dto.EmployeeInfoDTO;
import com.zoomcare.candidatechallenge.dto.OrganizationStructureDTO;
import com.zoomcare.candidatechallenge.dto.TopLevelListDTO;
import com.zoomcare.candidatechallenge.exception.EmployeeException;
import com.zoomcare.candidatechallenge.exception.EmployeeNotFoundException;
import com.zoomcare.candidatechallenge.model.Employee;
import com.zoomcare.candidatechallenge.repository.EmployeeRepository;

/**
 * Service for employees
 */
@Service
public class EmployeeService {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	/**
	 * Get employee information
	 * @param id - Employee ID
	 * @return Employee information
	 * @throws EmployeeException
	 */
	public EmployeeInfoDTO getEmployeeInformation(Long id) throws EmployeeException {
		LOG.info("Get employee information");
		Employee employee = getEmployeeById(id);
		
		if (null == employee) {
			throw new EmployeeNotFoundException(id);
		}
		
		EmployeeInfoDTO employeeDTO = convertEmployeeToEmployeeDTO(employee);
		
		if (null != employee.getSupervisorId()) {
			LOG.info("Get supervisor id: %d", employee.getSupervisorId());
			Employee supervisor = getEmployeeById(employee.getSupervisorId());
			employeeDTO.setSupervisor(convertEmployeeToEmployeeDTO(supervisor));
		}
		return employeeDTO;
	}
	
	/**
	 * Get all employees
	 * @return Employee list
	 */
	public List<TopLevelListDTO> getAllEmployees() {
		
		Iterable<Employee> employeeIterable = employeeRepository.getAllEmployees();
		List<OrganizationStructureDTO> organizationList = new ArrayList<>();
		Map<Long, List<OrganizationStructureDTO>> employeeOrg = new HashMap<>();
		
		employeeIterable.forEach(employee -> {
			organizationList.add(convertEmployeeToOrganizationDTO(employee));
		});
		
		organizationList.stream()
			.collect(Collectors.groupingBy(OrganizationStructureDTO::getSupervisor))
			.values()
			.stream()
			.forEach(emp -> {
				employeeOrg.put(emp.get(0).getSupervisor(), emp);
			});
		
		
		return mapOrganization(employeeOrg);
	}
	
	/**
	 * Get employee by ID
	 * @param id - Employee ID
	 * @return Employee
	 * @throws EmployeeException
	 */
	private Employee getEmployeeById(Long id) throws EmployeeException {
		return employeeRepository.getEmployeeById(id);
	}
	
	/**
	 * Convert employee to EmployeeDTO
	 * @param employee - Employee
	 * @return Employee DTO
	 */
	private EmployeeInfoDTO convertEmployeeToEmployeeDTO (Employee employee) {
		if (null != employee) {
			LOG.info("Information found");
			EmployeeInfoDTO employeeDTO = new EmployeeInfoDTO();
			
			employeeDTO.setId(employee.getId());
			employee.getProperties().forEach(emp -> {
				employeeDTO.getProperties().put(emp.getKey(), emp.getValue());
			});
			
			return employeeDTO;
		}
		
		return null;
	}
	
	/**
	 * Convert employee to OrganizationDTO
	 * @param employee - Employee
	 * @return OrganizationStructureDTO
	 */
	private OrganizationStructureDTO convertEmployeeToOrganizationDTO(Employee employee) {
		if (null != employee) {
			OrganizationStructureDTO organizationDTO = new OrganizationStructureDTO();
			organizationDTO.setId(employee.getId());
			organizationDTO.setSupervisor(employee.getSupervisorId());
			employee.getProperties().forEach(emp -> {
				organizationDTO.getProperties().put(emp.getKey(), emp.getValue());
			});
			
			return organizationDTO;
		}
		
		return null;
	}
	
	/**
	 * Create map organization for all employees
	 * @param organizationMap - map by supervisors
	 * @return Organization map nested by supervisor
	 */
	private List<TopLevelListDTO> mapOrganization(Map<Long, List<OrganizationStructureDTO>> organizationMap) {
		List<TopLevelListDTO> topLevelList = new ArrayList<>();
		List<OrganizationStructureDTO> firstLevel = organizationMap.get(-1l);
		
		firstLevel.forEach(first -> {
			TopLevelListDTO topLevelListDTO = new TopLevelListDTO();
			topLevelListDTO.setId(first.getId());
			topLevelListDTO.setProperties(first.getProperties());
			topLevelListDTO.getUnderling().addAll(mapNextLevels(first.getId(), organizationMap));
			topLevelList.add(topLevelListDTO);
		});
		
		return topLevelList;
	}

	/**
	 * Create map organization for nested levels
	 * @param supervisorId - Supervisor ID
	 * @param organizationMap - map by supervisors
	 * @return Organization map nested by supervisor
	 */
	private List<TopLevelListDTO> mapNextLevels(Long supervisorId,
			Map<Long, List<OrganizationStructureDTO>> organizationMap) {
		
		List<TopLevelListDTO> topLevelList = new ArrayList<>();
		List<OrganizationStructureDTO> nextLevel = organizationMap.get(supervisorId);
		
		nextLevel.forEach(next -> {
			TopLevelListDTO topLevelListDTO = new TopLevelListDTO();
			topLevelListDTO.setId(next.getId());
			topLevelListDTO.setProperties(next.getProperties());
			
			if (null != organizationMap.get(next.getId())) {
				topLevelListDTO.getUnderling().addAll(mapNextLevels(next.getId(), organizationMap));
			} 
			topLevelList.add(topLevelListDTO);
		});
		
		return topLevelList;
	}
	
}
