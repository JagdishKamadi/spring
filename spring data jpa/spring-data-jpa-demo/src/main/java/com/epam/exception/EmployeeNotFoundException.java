package com.epam.exception;

import com.epam.entity.Employee;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException()
    {
        super("Employee not found!");
    }

    public EmployeeNotFoundException(String str)
    {
        super(str);
    }
}
