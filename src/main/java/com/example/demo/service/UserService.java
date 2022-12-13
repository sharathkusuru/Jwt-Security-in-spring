package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	public Users saveUsers(Users users) {
		return userRepository.save(users);
	}

	public void deleteUsers(int userid) {
		userRepository.deleteById(userid);

	}



//	public Users getUsers(int userid) {
//
//		return userRepository.findById(userid);
//	}

}
