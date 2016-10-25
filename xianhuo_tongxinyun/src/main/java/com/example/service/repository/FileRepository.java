package com.example.service.repository;

import com.example.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface FileRepository extends JpaRepository<File, String> {
}
