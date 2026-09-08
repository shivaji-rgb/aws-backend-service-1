package com.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {
	@GetMapping("/java/")
	public String getLoin() {
		return "My spring boot app inside the private server";	}
	
	@GetMapping("/")
	public String getLogin() {
		return "My spring boot app inside the private server";	}


}
