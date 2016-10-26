package com.example.service.repository;

import com.example.entity.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by deado on 2016/10/22.
 */
@Repository
public interface ActivityRepository extends CrudRepository<Activity, String> {
}
