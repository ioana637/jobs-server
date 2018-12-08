package com.ubb.jobs.repo;

import com.ubb.jobs.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaRequestRepo extends JpaRepository<Request, Integer> {
    List<Request> findRequestByUserTo(Integer id);

    List<Request> findRequestByUserFrom(Integer id);
}