package com.zoomcare.candidatechallenge.service;

import com.zoomcare.candidatechallenge.dao.EmployeeDAO;
import com.zoomcare.candidatechallenge.repository.Employee;
import com.zoomcare.candidatechallenge.util.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO empDao;

    public List<Employee> getAllEmployee() {
        List<Employee> listEmployee = empDao.findAll();
        if (!listEmployee.isEmpty()) {
            chargeGraph(listEmployee, null, true);
            return listEmployee;
        }
        return listEmployee;
    }

    public Optional<Employee> getEmployeeId(BigInteger employeeId) {
        Optional<Employee> empl = empDao.findById(employeeId);
        List<Employee> listEmployee = empDao.findAll();
        if (empl.get().getSupervisorId() != null) {
            chargeGraph(listEmployee, employeeId, false);
            return empl;
        }
        return empl;
    }

    private void chargeGraph(List<Employee> listEmployee, BigInteger employeeId, Boolean all) {
        int size = listEmployee.size() + 1;
        Graph graph = new Graph(size);
        listEmployee.forEach(a -> {
            if (a.getSupervisorId() != null) {
                graph.addEdge(a.getId().intValue(), a.getSupervisorId().intValue());
            } else {
                graph.addEdge(a.getId().intValue(), 0);
            }
        });
        if (all.equals(false)) {
            DFS(employeeId.intValue(), size, graph.getAdj());
        } else {
            listEmployee.forEach(a ->
                    DFS(a.getId().intValue(), size, graph.getAdj()));
        }
    }

    public void DFS(int empId, int size, LinkedList<Integer>[] list) {
        boolean nodes[] = new boolean[size];
        Stack<Integer> stack = new Stack<>();
        stack.push(empId);
        int sup = 0;

        while (!stack.empty()) {
            empId = stack.peek();                    //extract the top element of the stack
            stack.pop();                            //remove the top element

            if (nodes[empId] == false) nodes[empId] = true;
            for (int i = 0; i < list[empId].size(); i++) {
                sup = list[empId].get(i);
                if (!nodes[sup]) {
                    stack.push(sup);
                    addChild(sup, empId);
                }
            }
        }
    }

    private void addChild(int sup, int curr) {
        Optional<Employee> supervisor = empDao.findById(BigInteger.valueOf(sup));
        Optional<Employee> employee = empDao.findById(BigInteger.valueOf(curr));
        employee.get().setDirectReport(supervisor);
    }
}
