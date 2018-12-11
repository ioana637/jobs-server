package com.ubb.jobs.repo;

import com.ubb.jobs.model.JobAbility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJobAbilityRepo extends JpaRepository<JobAbility, Integer> {
    JobAbility getJobAbilityByAbility_IdAndJob_Id(Integer abilityId, Integer jobId);

    Integer removeAllByJob_Id(Integer jobId);
}
