package com.epam.controller;

import com.epam.dtos.EmployeeDTO;
import com.epam.exception.EmployeeNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeController employeeController;

    private EmployeeDTO employeeDTO;

    private String empStringDataJSON;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception {
        employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Steven Rogger");
        employeeDTO.setEmail("stevenrogger@gmail.com");
        employeeDTO.setAge(21);
        employeeDTO.setDepartment("Accountant");

        objectMapper = new ObjectMapper();

        empStringDataJSON = objectMapper.writeValueAsString(employeeDTO);
    }

    @Test
    void test_getEmployee() throws Exception {
        int id = 1;
        when(employeeController.getEmployee(id)).thenReturn(new ResponseEntity<>(employeeDTO, HttpStatus.OK));
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void test_getEmployee_whenIdIsNotValid_thenThrowsException() throws Exception {
        int id = -1;
        when(employeeController.getEmployee(id)).thenThrow(new EmployeeNotFoundException());
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    void test_getEmployees() throws Exception {
        when(employeeController.getEmployees()).thenReturn(new ResponseEntity<>(List.of(employeeDTO), HttpStatus.OK));
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    void test_saveEmployee() throws Exception {
        when(employeeController.saveEmployee(employeeDTO)).thenReturn(new ResponseEntity<>(employeeDTO, HttpStatus.CREATED));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employees")
                .accept(MediaType.APPLICATION_JSON)
                .content(empStringDataJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    void test_updateEmployee() throws Exception {
        int id = 1;
        when(employeeController.updateEmployee(id, employeeDTO)).thenReturn(new ResponseEntity<>(employeeDTO, HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employees/{employee_id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .content(empStringDataJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void test_deleteEmployee() throws Exception {
        int id = 1;
        when(employeeController.deleteEmployee(id)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void test_getEmployeesByName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/findByName")
                        .param("name", "Jagdish"))
                .andExpect(status().isOk());
    }

    @Test
    void test_getEmployeesByNameAndDepartment() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/findByNameAndDepartment")
                        .param("name", "Jagdish")
                        .param("department", "Accountant"))
                .andExpect(status().isOk());
    }

    @Test
    void test_getEmployeeNameByContaining() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/getEmployeeNameByContaining")
                        .param("pattern", "dish"))
                .andExpect(status().isOk());
    }
}