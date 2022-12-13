package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	List<Users> getallUser = null;

	Users findByUsername(String username);


}
