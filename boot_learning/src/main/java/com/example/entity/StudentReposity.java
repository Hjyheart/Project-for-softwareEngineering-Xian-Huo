package com.example.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hongjiayong on 2016/10/18.
 */
public interface StudentReposity extends JpaRepository<Student, Long> {
    Student findByName(String name);
    Student findById(String id);

    @Query("from Student s where s.name=:name")
    Student findStudent(@Param("name") String name);
}
