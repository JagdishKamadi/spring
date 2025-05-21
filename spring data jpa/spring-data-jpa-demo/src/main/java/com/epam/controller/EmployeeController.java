package com.epam.controller;

import com.epam.dtos.EmployeeDTO;
import com.epam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{employee_id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("employee_id") Integer employeeId, @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(employeeId);
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByName(@RequestParam String name) {
        return new ResponseEntity<>(employeeService.getEmployeesByName(name), HttpStatus.OK);
    }

    @GetMapping("/findByNameAndDepartment")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByNameAndDepartment(@RequestParam String name, @RequestParam String department) {
        return new ResponseEntity<>(employeeService.getEmployeesByNameAndDepartment(name, department), HttpStatus.OK);
    }

    @GetMapping("/getEmployeeNameByContaining")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeNameByContaining(@RequestParam String pattern) {
        return new ResponseEntity<>(employeeService.getEmployeeNameByContaining(pattern), HttpStatus.OK);
    }

    // Pagination Controller
    @GetMapping("/{page_number}/{page_size}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeWithPageSize(@PathVariable("page_number") Integer pageNumber,
                                                                     @PathVariable("page_size") Integer pageSize) {
        return new ResponseEntity<>(employeeService.getEmployees(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/getEmployeeNameByReverseIdOrder")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeNameByReverseIdOrder(@RequestParam String pattern) {
        return new ResponseEntity<>(employeeService.getEmployeeNameByContainingSorted(pattern), HttpStatus.OK);
    }
}

