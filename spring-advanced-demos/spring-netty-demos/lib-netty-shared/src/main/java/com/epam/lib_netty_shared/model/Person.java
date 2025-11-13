package com.epam.lib_netty_shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 12121L;
    private Integer id;
    private String firstname;
    private String lastname;
}
