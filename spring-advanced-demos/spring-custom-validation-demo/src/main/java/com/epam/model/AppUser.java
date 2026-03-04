package com.epam.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String phoneNumber;
    @Email
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
