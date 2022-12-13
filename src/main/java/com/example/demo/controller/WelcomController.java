package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthRequest;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

@RestController
public class WelcomController {
	@Autowired
	UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	

	@GetMapping("/hii")
	public String welcome() {
		return "Welcome to security !!";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUsername());
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthorize('user')")
	private List<Users> getAllUser() {
		return userService.getAllUsers();
	}

	@PostMapping("/create")
	private int save(@RequestBody Users users) {
		userService.saveUsers(users);
		return users.getUserid();
	}

	

}
