package com.epam.service;

import com.epam.entity.Employee;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(Integer id);

    List<Employee> getEmployees();
    List<Employee> getEmployees(Integer pageNumber,Integer pageSize);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getEmployeesByNameAndDepartment(String name,String department);

    List<Employee> getEmployeeNameByContaining(String pattern);
    List<Employee> getEmployeeNameByContainingSorted(String pattern);
}
