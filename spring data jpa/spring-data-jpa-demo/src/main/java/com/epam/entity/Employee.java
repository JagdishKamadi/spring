package com.epam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JsonProperty("full_name")
    @NotBlank(message = "Employee name should not be blank")
    private String name;
    private Integer age;
    private String location;
    //  if you apply this you will not get email in response body
    //  @JsonIgnore
    //   @Email validate @NotBlank
    @Email(message = "Email should be in correct format")
    private String email;

    @NotBlank(message = "Employee department should not be blank")
    private String department;

    //  now we are adding the timestamp also
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;
}
