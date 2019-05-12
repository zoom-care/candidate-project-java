package com.zoomcare.candidatechallenge.integrationtests;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoomcare.candidatechallenge.models.Employee;
import com.zoomcare.candidatechallenge.models.Property;
import com.zoomcare.candidatechallenge.models.PropertyIdentifier;
import com.zoomcare.candidatechallenge.repositories.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private EmployeeRepository employeeRepository;

	private Employee bigKahuna;
	private Employee subOrd1;
	private Employee subOrd2;

	@Before
	public void setUpTestHarness() {

		bigKahuna = new Employee();
		bigKahuna.setId(100L);
		bigKahuna.addTestProperty("title", "President");
		bigKahuna.addTestProperty("region", "World");

		subOrd1 = new Employee();
		subOrd1.setId(110L);
		subOrd1.setSupervisor(bigKahuna);
		subOrd1.addTestProperty("title", "COO");
		subOrd1.addTestProperty("region", "World");

		subOrd2 = new Employee();
		subOrd2.setId(120L);
		subOrd2.setSupervisor(bigKahuna);
		subOrd2.addTestProperty("title", "CFO");
		subOrd2.addTestProperty("region", "USA");

		entityManager.persist(bigKahuna);
		entityManager.persist(subOrd1);
		entityManager.persist(subOrd2);
		entityManager.flush();

	}

	public void tearDownTestHarness() {

		entityManager.remove(subOrd1);
		entityManager.remove(subOrd2);
		entityManager.remove(bigKahuna);
		entityManager.flush();

	}

	@Test
	public void whenLookForTopLevel_ReturnTopLevel() {

		// when searching for top level employees
		List<Employee> topDogs = employeeRepository.findBySupervisorIsNull();

		// then ensure we get the big kahuna
		assert (topDogs.contains(bigKahuna));

		// then ensure we do not get any non top level employees returned in the list
		assert (!topDogs.contains(subOrd1));

	}

	@Test
	public void whenLookById_ReturnSubOrdEmployee() {

		// when searching for a non top level employee by Id
		Optional<Employee> subOrd1RetrievedOptional = employeeRepository.findById(110L);

		// then ensure the employee is returned
		assert (subOrd1RetrievedOptional.isPresent());
		Employee subOrd1Retrieved = subOrd1RetrievedOptional.get();

		// then ensure supervisor is returned
		assert (subOrd1Retrieved.getSupervisor().equals(bigKahuna));

		// then ensure Properties are returned for the non top level employee
		Set<Property> props = subOrd1Retrieved.getProperties();
		assert (props != null && props.size() == 2);

		PropertyIdentifier testPropIdentifier = new PropertyIdentifier();
		testPropIdentifier.setEmployeeId(110L);
		testPropIdentifier.setKey("title");
		Property testProp = new Property();
		testProp.setId(testPropIdentifier);
		testProp.setValue("COO");

		assert (props.contains(testProp));

	}

	@Test
	public void whenLookById_ReturnTopLevelEmployee() {

		// when searching for a top level employee by Id
		Optional<Employee> topLevelEmpOptional = employeeRepository.findById(100L);

		// then ensure the employee is returned
		assert (topLevelEmpOptional.isPresent());
		Employee topLevelEmp = topLevelEmpOptional.get();

		// then ensure supervisor is null
		assert (topLevelEmp.getSupervisor() == null);

		// then ensure Properties are returned for the top level employee
		Set<Property> props = topLevelEmp.getProperties();
		assert (props != null && props.size() == 2);

		PropertyIdentifier testPropIdentifier = new PropertyIdentifier();
		testPropIdentifier.setEmployeeId(100L);
		testPropIdentifier.setKey("title");
		Property testProp = new Property();
		testProp.setId(testPropIdentifier);
		testProp.setValue("President");

		assert (props.contains(testProp));

	}

}