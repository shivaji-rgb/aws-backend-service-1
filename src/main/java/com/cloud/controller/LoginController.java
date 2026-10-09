package com.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.dto.request.RegisterUserRequest;
import com.cloud.entity.users.Users;
import com.cloud.service.UserService;

import jakarta.validation.Valid;

@RestController
public class LoginController {
	
	@Autowired
	private UserService service;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		if (request.username() == null || request.username().isBlank()
				|| request.password() == null || request.password().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new LoginResponse(false, "Username and password are required"));
		}

		return ResponseEntity.ok(new LoginResponse(true, "Login request accepted"));
	}

	public record LoginRequest(String username, String password) {
	}

	public record LoginResponse(boolean authenticated, String message) {
	}
	
	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody @Valid RegisterUserRequest req) {
		
	
		
		return ResponseEntity.status(HttpStatus.CREATED)
	            .body(service.registerUser(req));
		
	}
	
}