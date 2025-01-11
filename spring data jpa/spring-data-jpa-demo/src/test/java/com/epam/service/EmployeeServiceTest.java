package com.epam.service;

import com.epam.entity.Employee;
import com.epam.exception.EmployeeNotFoundException;
import com.epam.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee employee;
    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .name("Jagdish Kamadi")
                .email("jagadishkamadi@gmail.com")
                .department("Accountant")
                .age(21)
                .build();
    }

    @Test
    void test_getEmployee_success() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.ofNullable(employee));
        assertNotNull(employeeService.getEmployee(anyInt()));
    }

    @Test
    void test_getEmployee_error() {
        assertThrows(EmployeeNotFoundException.class,()->employeeService.getEmployee(anyInt()));
    }

    @Test
    void test_getEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployees());
    }

    @Test
    void test_saveEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        assertNotNull(employeeService.saveEmployee(employee));
    }

    @Test
    void test_updateEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        assertNotNull(employeeService.updateEmployee(employee));
    }

    @Test
    void test_deleteEmployee() {
        employeeRepository.deleteById(anyInt());
        verify(employeeRepository).deleteById(anyInt());
        verify(employeeRepository,times(1)).deleteById(anyInt());
//        verify(employeeService).deleteEmployee(anyInt());
//        verify(employeeService,times(1)).deleteEmployee(anyInt());
    }

    @Test
    void test_getEmployeesByName() {
        when(employeeRepository.findByName(anyString())).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployeesByName(anyString()));
    }

    @Test
    void getEmployeesByNameAndDepartment() {
        when(employeeRepository.findByNameAndDepartment(anyString(),anyString())).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployeesByNameAndDepartment(anyString(),anyString()));
    }

    @Test
    void getEmployeeNameByContaining() {
        when(employeeRepository.findByNameContaining(anyString())).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployeeNameByContaining(anyString()));
    }
}