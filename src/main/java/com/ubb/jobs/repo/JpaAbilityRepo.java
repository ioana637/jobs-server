package com.ubb.jobs.repo;

import com.ubb.jobs.model.Ability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAbilityRepo extends JpaRepository<Ability, Integer> {
    List<Ability> findAll();
}
