package com.epam.service;

import com.epam.dtos.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO getEmployee(Integer id);

    List<EmployeeDTO> getEmployees();

    List<EmployeeDTO> getEmployees(Integer pageNumber, Integer pageSize);

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    void deleteEmployee(Integer id);

    List<EmployeeDTO> getEmployeesByName(String name);

    List<EmployeeDTO> getEmployeesByNameAndDepartment(String name, String department);

    List<EmployeeDTO> getEmployeeNameByContaining(String pattern);

    List<EmployeeDTO> getEmployeeNameByContainingSorted(String pattern);
}
