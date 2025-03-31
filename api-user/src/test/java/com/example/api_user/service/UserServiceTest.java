package com.example.api_user.service;

import com.example.api_user.exception.InvalidUserException;
import com.example.api_user.exception.UserNotFoundException;
import com.example.api_user.model.User;
import com.example.api_user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository; // Mock for the repository
    private UserService userService; // Service to be tested

    @BeforeEach
    void setUp() {
        // Create a mock repository and initialize the service with it
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void createUser_shouldSaveUser_whenValid() {
        // Arrange: Create a valid user
        User user = new User();
        user.setName("Zinedine Zidane");
        user.setBirthdate(LocalDate.of(2000, 1, 1));
        user.setCountry("France");

        // Stub: Simulate the repository behavior
        when(userRepository.existsByNameAndBirthdate(user.getName(), user.getBirthdate())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act: Call the method to create a user
        User savedUser = userService.createUser(user);

        // Assert: Verify that the user is saved correctly
        assertNotNull(savedUser);
        assertEquals("Zinedine Zidane", savedUser.getName());
        verify(userRepository, times(1)).save(user); // Ensure the save method is called once
    }

    @Test
    void createUser_shouldThrowException_whenNameIsInvalid() {
        // Arrange: Create a user with an invalid name
        User user = new User();
        user.setName(""); // Invalid name
        user.setBirthdate(LocalDate.of(2000, 1, 1));
        user.setCountry("France");

        // Act & Assert: Expect an InvalidUserException to be thrown
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userService.createUser(user));
        assertEquals("Name is required.", exception.getMessage());
        verify(userRepository, never()).save(any()); // Ensure the save method is not called
    }

    @Test
    void createUser_shouldThrowException_whenUserExists() {
        // Arrange: Create a user that already exists
        User user = new User();
        user.setName("Zinedine Zidane");
        user.setBirthdate(LocalDate.of(2000, 1, 1));
        user.setCountry("France");

        // Stub: Simulate the user already existing in the repository
        when(userRepository.existsByNameAndBirthdate(user.getName(), user.getBirthdate())).thenReturn(true);

        // Act & Assert: Expect an InvalidUserException to be thrown
        InvalidUserException exception = assertThrows(InvalidUserException.class, () -> userService.createUser(user));
        assertEquals("A user with this name and birthdate already exists.", exception.getMessage());
        verify(userRepository, never()).save(any()); // Ensure the save method is not called
    }

    @Test
    void getUserById_shouldReturnUser_whenUserExists() {
        // Arrange: Create a user and mock the repository
        User user = new User();
        user.setId(1L);
        user.setName("Zinedine Zidane");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        // Act: Retrieve the user by ID
        User retrievedUser = userService.getUserById(1L);

        // Assert: Verify the correct user is returned
        assertNotNull(retrievedUser);
        assertEquals(1L, retrievedUser.getId());
        assertEquals("Zinedine Zidane", retrievedUser.getName());
    }

    @Test
    void getUserById_shouldThrowException_whenUserDoesNotExist() {
        // Stub: Simulate a user not being found in the repository
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act & Assert: Expect a UserNotFoundException to be thrown
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
        assertEquals("User not found with ID: 1", exception.getMessage());
    }
}