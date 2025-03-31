package com.example.api_user.repository;

import com.example.api_user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Check if a user with the same name and birthdate exists
    boolean existsByNameAndBirthdate(String name, LocalDate birthdate);

    // Search for a user by name
    Optional<User> findByName(String name);
}