package com.siemens.company.model;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Value //Getters, ToString, EqualsAndHashCode,
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //for Integer you have to have IDENTITY here
    private Integer id;
    private String name;

    public Employee(){
        id = 0;
        name = null;
    }
}
