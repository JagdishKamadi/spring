package com.epam.service;

import com.epam.dtos.EmployeeDTO;
import com.epam.entity.Employee;
import com.epam.exception.EmployeeNotFoundException;
import com.epam.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDTO getEmployee(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found for id " + id));
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee updatedEmployee = employeeRepository.save(employee);
        return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByName(String name) {
        List<Employee> employee = employeeRepository.findByName(name);
        return employee.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByNameAndDepartment(String name, String department) {
        List<Employee> employee = employeeRepository.findByNameAndDepartment(name, department);
        return employee.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeNameByContaining(String pattern) {
        List<Employee> employee = employeeRepository.findByNameContaining(pattern);
        return employee.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }

    // Pagination Method
    @Override
    public List<EmployeeDTO> getEmployees(Integer pageNumber, Integer pageSize) {
        // pass sort object here itself
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        // get content gives us list of employee
        List<Employee> employees = employeeRepository.findAll(pageable).getContent();
        return employees.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeNameByContainingSorted(String pattern) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Employee> employees = employeeRepository.findByNameContaining(pattern, sort);
        return employees.stream().map(e -> modelMapper.map(e, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
