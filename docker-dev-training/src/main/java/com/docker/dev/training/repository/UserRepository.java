package com.docker.dev.training.repository;

import com.docker.dev.training.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Find a user by username.
     *
     * @param username the username to search for
     * @return the User object, or null if not found
     */
    User findByUsername(String username);
}
