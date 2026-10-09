package com.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.dto.request.RegisterUserRequest;
import com.cloud.entity.users.Users;
import com.cloud.repoimpl.UserRepoImp;

@Service
public class UserService {

	@Autowired
	private UserRepoImp repo;
	
	@Transactional
	public ResponseEntity registerUser(RegisterUserRequest req) {
		Users user = new Users();
		user.setUsername(req.getUsername());
		//need to endode 
		user.setPasswordHash(req.getPassword());
		ResponseEntity res = repo.registerUser(user);
		
		return ResponseEntity.ok(res);
		
	}
}
