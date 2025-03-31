package com.example.api_user.service;

import com.example.api_user.exception.InvalidUserException;
import com.example.api_user.exception.UserNotFoundException;
import com.example.api_user.model.User;
import com.example.api_user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        validateUser(user);

        // Check if a user with the same name and birthdate already exists
        if (userRepository.existsByNameAndBirthdate(user.getName(), user.getBirthdate())) {
            throw new InvalidUserException("A user with this name and birthdate already exists.");
        }

        user.setId(null); // Prevent manual insertion of ID
        User savedUser = userRepository.save(user);
        logger.info("User successfully created: {}", savedUser);
        return savedUser;
    }

    public List<User> getAllUsers() {
        // Retrieve all users from the repository
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        // Retrieve a user by ID or throw an exception if not found
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    public void deleteUser(Long id) {
        // Check if the user exists before attempting deletion
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Cannot delete: user not found.");
        }
        userRepository.deleteById(id);
        logger.info("User deleted with ID: {}", id);
    }

    private void validateUser(User user) {
        // Ensure the user name is provided
        if (user.getName() == null || user.getName().isBlank()) {
            throw new InvalidUserException("Name is required.");
        }

        // Ensure the user is an adult
        if (!user.isAdult()) {
            throw new InvalidUserException("User must be an adult.");
        }

        // Ensure the user is a French resident
        if (!user.isFrenchResident()) {
            throw new InvalidUserException("User must be a French resident.");
        }
    }
}