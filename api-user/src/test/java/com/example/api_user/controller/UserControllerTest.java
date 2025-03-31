package com.example.api_user.controller;

import com.example.api_user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Loads the application context for integration tests
@ActiveProfiles("test") // Ensures the test profile is used
class UserControllerIntegrationTest {

    @Autowired
    private UserController userController; // Inject the controller being tested

    @Test
    void createUser_andRetrieveUsers() {
        // Arrange: Create a new user
        User user = new User();
        user.setName("Laroche Martin");
        user.setBirthdate(LocalDate.of(1995, 5, 15));
        user.setCountry("France");

        // Act: Call the createUser endpoint
        ResponseEntity<User> response = userController.createUser(user);

        // Assert: Verify the user is created successfully
        assertEquals(200, response.getStatusCodeValue());
        User createdUser = response.getBody();
        assertNotNull(createdUser);
        assertEquals("Laroche Martin", createdUser.getName());

        // Act: Call the getAllUsers endpoint
        ResponseEntity<List<User>> usersResponse = userController.getAllUsers();

        // Assert: Verify the created user is in the list
        List<User> users = usersResponse.getBody();
        assertNotNull(users);
        assertTrue(users.stream().anyMatch(u -> "Laroche Martin".equals(u.getName())));
    }

    @Test
    void deleteUser_shouldRemoveUser() {
        // Arrange: Create a new user
        User user = new User();
        user.setName("Eric Cantona");
        user.setBirthdate(LocalDate.of(1980, 3, 10));
        user.setCountry("France");

        // Act: Call the createUser endpoint
        ResponseEntity<User> createResponse = userController.createUser(user);
        assertEquals(200, createResponse.getStatusCodeValue());
        Long userId = createResponse.getBody().getId();

        // Act: Call the deleteUser endpoint
        ResponseEntity<Void> deleteResponse = userController.deleteUser(userId);

        // Assert: Verify the user is deleted successfully
        assertEquals(204, deleteResponse.getStatusCodeValue());

        // Act: Call the getAllUsers endpoint
        ResponseEntity<List<User>> usersResponse = userController.getAllUsers();

        // Assert: Verify the user is no longer in the list
        List<User> users = usersResponse.getBody();
        assertNotNull(users);
        assertFalse(users.stream().anyMatch(u -> u.getId().equals(userId)));
    }
}