package com.epam.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Integer id;

    private String name;

    private Integer age;

    private String location;

    private String email;

    private String department;

    private Date createdAt;

    private Date updatedAt;
}
