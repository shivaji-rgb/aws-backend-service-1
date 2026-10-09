package com.cloud.dto.request;

import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

	@Data
	public class RegisterUserRequest {

	    @NotBlank(message = "Username is required")
	    @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters")
	    private String username;

	    @NotBlank(message = "Password is required")
	    @Size(min = 8, max = 100, message = "Password must be at least 8 characters")
	    private String password;

	    @Email(message = "Please provide a valid email address")
	    private String email;

	    @Pattern(
	        regexp = "^[0-9]{10}$",
	        message = "Phone number must contain 10 digits"
	    )
	    private String phone;
	}
	
