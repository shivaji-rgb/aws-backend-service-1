package com.cloud.repoimpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cloud.entity.users.Users;
import com.cloud.repository.UserRepositoryInterface;

@Repository
public class UserRepoImp {

	private UserRepositoryInterface repo;
	
	public UserRepoImp(UserRepositoryInterface repo) {
		this.repo = repo;
	}
	
	
	
	public ResponseEntity registerUser(Users user) {
		
		return ResponseEntity.ok(repo.save(user));
	}
}
