package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.UserEntity;
import com.example.demo.jwt.SecurityUtil;
import com.example.demo.request.UserLoginRequest;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {
	@Autowired
	UserService userService;


	@PostMapping("/register")
	public void register(@RequestBody UserEntity userEntity) {
		userService.register(userEntity);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest userLoginRequest) {
		String token = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());

		return ResponseEntity.ok(Map.of("token", token));
	}

	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		String token=header.substring(7);
		userService.logout(token);
	}
	@GetMapping("/me")
	public UserEntity me() {
		String username=SecurityUtil.getCurrentUsername();
		return userService.getUserInfo(username);
	}
}