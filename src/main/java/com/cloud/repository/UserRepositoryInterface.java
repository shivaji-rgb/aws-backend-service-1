package com.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.cloud.entity.users.Users;

public interface UserRepositoryInterface extends JpaRepository<Users,Long>{

}
