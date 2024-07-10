package com.ems.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.api.dto.LoginForm;
import com.ems.api.dto.UserResponse;
import com.ems.api.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody LoginForm form) {
		return new ResponseEntity<>(service.login(form), HttpStatus.OK);
	}

}
