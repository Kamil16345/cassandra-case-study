package com.softwaremind.demo.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.softwaremind.demo.model.security.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    @Query("SELECT * FROM users WHERE username = ?0 ALLOW FILTERING")
    User findByUsername(String username);
}
