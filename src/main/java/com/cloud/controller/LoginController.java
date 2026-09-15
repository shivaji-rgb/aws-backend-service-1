package com.cloud.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

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
}