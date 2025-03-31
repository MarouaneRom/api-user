package com.example.api_user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotNull(message = "Birthdate is required.")
    private LocalDate birthdate;

    @NotBlank(message = "Country is required.")
    @Size(max = 50, message = "Country cannot exceed 50 characters.")
    private String country;

    @Pattern(
            regexp = "^\\+?[0-9]*$", // Allows only numbers and a "+" at the start
            message = "Phone number must be valid."
    )
    @Size(max = 15, message = "Phone number cannot exceed 15 characters.")
    private String phoneNumber; // Optional field

    private String gender; // Optional field without strict validation rules

    // Business logic method to check if the user is an adult
    public boolean isAdult() {
        return Period.between(this.birthdate, LocalDate.now()).getYears() >= 18;
    }

    // Business logic method to check if the user is a French resident
    public boolean isFrenchResident() {
        return "France".equalsIgnoreCase(this.country);
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}