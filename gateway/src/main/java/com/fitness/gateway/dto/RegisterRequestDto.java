package com.fitness.gateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String email;
    private String keycloakId;

    @NotBlank(message= "Password is required")
    @Size(min = 6, message = "Password must be at-least 6 characters")
    private String password;

    private String firstName;
    private String lastName;
}
