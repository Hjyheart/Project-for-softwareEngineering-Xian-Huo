package com.example.service.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    //Student findByName(String name);
    //Student findById(String id);

    @Query("from Student s where s.NAME=:name")
    Student findStudent(@Param("name") String name);
}
