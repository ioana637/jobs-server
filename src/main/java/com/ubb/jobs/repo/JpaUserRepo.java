package com.ubb.jobs.repo;

import com.ubb.jobs.model.Role;
import com.ubb.jobs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserRepo extends JpaRepository<User, Integer> {

    // example of custom method on jpa, the query is made by the spring data, you don't need to worry about it for most cases
    User findUserByUsernameAndPassword(String username, String password);
    Page<User> findAllByRoleEquals(Role role, Pageable pageable);
    List<User> findAllByRole(Role role);
    List<User> findAllByRoleAndStarAvgIsGreaterThanEqual(Role role, Float avg);
}
