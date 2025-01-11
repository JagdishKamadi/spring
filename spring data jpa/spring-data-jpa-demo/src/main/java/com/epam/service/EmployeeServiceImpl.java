package com.epam.service;

import com.epam.entity.Employee;
import com.epam.exception.EmployeeNotFoundException;
import com.epam.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found for id "+id));
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> getEmployeesByNameAndDepartment(String name, String department) {
        return employeeRepository.findByNameAndDepartment(name, department);
    }

    @Override
    public List<Employee> getEmployeeNameByContaining(String pattern) {
        return employeeRepository.findByNameContaining(pattern);
    }

    // Pagination Method
    @Override
    public List<Employee> getEmployees(Integer pageNumber, Integer pageSize) {
        // pass sort object here itself
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.Direction.DESC,"id");
        // get content gives us list of employee
        return employeeRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Employee> getEmployeeNameByContainingSorted(String pattern){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return employeeRepository.findByNameContaining(pattern,sort);
    }
}
