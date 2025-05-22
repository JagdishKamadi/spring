package com.epam.service;

import com.epam.dtos.EmployeeDTO;
import com.epam.entity.Employee;
import com.epam.exception.EmployeeNotFoundException;
import com.epam.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee employee;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(1)
                .name("Black Widow")
                .email("blackwidow@gmail.com")
                .department("Spying")
                .age(34)
                .location("New York")
                .build();

        employeeDTO = EmployeeDTO.builder()
                .id(1)
                .name("Black Widow")
                .email("blackwidow@gmail.com")
                .department("Spying")
                .age(34)
                .location("New York")
                .build();
    }

    @Test
    void test_getEmployee_whenIdIsValid() {
        int id = 1;
        when(employeeRepository.findById(id)).thenReturn(Optional.ofNullable(employee));
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);
        EmployeeDTO actualEmployee = employeeService.getEmployee(id);
        assertNotNull(actualEmployee);
        assertEquals(1, actualEmployee.getId());
    }

    @Test
    void test_getEmployee_whenIdIsInvalid_thenThrowsException() {
        int id = -1;
        when(employeeRepository.findById(id)).thenThrow(new EmployeeNotFoundException());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(id));
    }

    @Test
    void test_getEmployees() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployees());
    }

    @Test
    void test_saveEmployee() {
        when(modelMapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);
        EmployeeDTO actualEmployeeDTO = employeeService.saveEmployee(employeeDTO);
        assertNotNull(actualEmployeeDTO);
        assertAll("Checking all possible field", () -> assertEquals("Black Widow", employeeDTO.getName()),
                () -> assertEquals(34, employeeDTO.getAge()),
                () -> assertEquals("Spying", employeeDTO.getDepartment())
        );
    }

    @Test
    void test_updateEmployee() {
        when(modelMapper.map(employeeDTO, Employee.class)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);
        when(modelMapper.map(employee, EmployeeDTO.class)).thenReturn(employeeDTO);
        EmployeeDTO actualEmployeeDTO = employeeService.updateEmployee(employeeDTO);
        assertNotNull(actualEmployeeDTO);
    }

    @Test
    void test_deleteEmployee() {
        employeeRepository.deleteById(anyInt());
        verify(employeeRepository).deleteById(anyInt());
        verify(employeeRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void test_getEmployeesByName() {
        when(employeeRepository.findByName(anyString())).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployeesByName(anyString()));
    }

    @Test
    void getEmployeesByNameAndDepartment() {
        when(employeeRepository.findByNameAndDepartment(anyString(), anyString())).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployeesByNameAndDepartment(anyString(), anyString()));
    }

    @Test
    void getEmployeeNameByContaining() {
        when(employeeRepository.findByNameContaining(anyString())).thenReturn(List.of(employee));
        assertNotNull(employeeService.getEmployeeNameByContaining(anyString()));
    }
}