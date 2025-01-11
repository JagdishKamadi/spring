package com.epam.controller;

import com.epam.entity.Employee;
import com.epam.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Employee employee;
    private String empStringDataJSON;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws Exception{
        employee = new Employee();
        employee.setName("Jagdish Kamadi");
        employee.setEmail("jagadishkamadi@gmail.com");
        employee.setAge(21);
        employee.setDepartment("Accountant");

        objectMapper = new ObjectMapper();

        empStringDataJSON = objectMapper.writeValueAsString(employee);
    }

    @Test
    void test_getEmployee() throws Exception{
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}",anyInt()))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200,status);
    }

    @Test
    void test_getEmployees() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200,status);

    }

    @Test
    void test_saveEmployee() throws Exception{
       RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/employees")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(empStringDataJSON)
                                    .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(201,status);
    }

    @Test
    void test_updateEmployee() throws Exception{
        RequestBuilder requestBuilder= MockMvcRequestBuilders.put("/employees/{employee_id}",anyInt())
                .accept(MediaType.APPLICATION_JSON)
                .content(empStringDataJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = this.mockMvc.perform(requestBuilder).andReturn();
        int status = result.getResponse().getStatus();
        assertEquals(200,status);
    }

    @Test
    void test_deleteEmployee() throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void test_getEmployeesByName() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/findByName")
                        .param("name","Jagdish"))
                .andExpect(status().isOk());
    }

    @Test
    void test_getEmployeesByNameAndDepartment() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/findByNameAndDepartment")
                        .param("name","Jagdish")
                        .param("department","Accountant"))
                .andExpect(status().isOk());
    }

    @Test
    void test_getEmployeeNameByContaining() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees/getEmployeeNameByContaining")
                        .param("pattern","dish"))
                .andExpect(status().isOk());
    }
}