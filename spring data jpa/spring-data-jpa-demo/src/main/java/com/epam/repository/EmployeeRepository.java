package com.epam.repository;

import com.epam.entity.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    // SELECT * FROM employee WHERE name=?
    List<Employee> findByName(String name);

    // SELECT * FROM employee WHERE name=? AND department=?
    List<Employee> findByNameAndDepartment(String name,String department);

    // SELECT * FROM employee WHERE name like %pattern%
    List<Employee> findByNameContaining(String pattern);
    List<Employee> findByNameContaining(String pattern, Sort sort);
}
