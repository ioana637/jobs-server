package com.ubb.jobs.repo;

import com.ubb.jobs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaJobRepo extends JpaRepository<Job, Integer> {
    List<Job> findJobsByIdClient(Integer id);

}
