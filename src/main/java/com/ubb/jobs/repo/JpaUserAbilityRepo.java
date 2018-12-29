package com.ubb.jobs.repo;

import com.ubb.jobs.model.UserAbilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserAbilityRepo extends JpaRepository<UserAbilities, Integer> {

    List<UserAbilities> findAllByUser_Id(Integer id);

    Integer removeAllByUser_Id(Integer id);
}
