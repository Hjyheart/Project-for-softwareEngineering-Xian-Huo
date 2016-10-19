package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hongjiayong on 2016/10/17.
 */
@Entity
public class Student{

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }
}
