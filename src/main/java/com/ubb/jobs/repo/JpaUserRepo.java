package com.ubb.jobs.repo;

import com.ubb.jobs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepo extends JpaRepository<User, Integer> {

    // example of custom method on jpa, the query is made by the spring data, you don't need to worry about it for most cases
    User findUserByUsernameAndPassword(String username, String password);
}
