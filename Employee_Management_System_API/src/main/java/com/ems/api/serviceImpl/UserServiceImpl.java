package com.ems.api.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.api.dto.LoginForm;
import com.ems.api.dto.UserResponse;
import com.ems.api.entity.UserEntity;
import com.ems.api.exception.BadRequestException;
import com.ems.api.exception.InActiveException;
import com.ems.api.exception.InvalidException;
import com.ems.api.exception.NotFoundException;
import com.ems.api.repository.UserRepository;
import com.ems.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserResponse login(LoginForm login) {
		UserResponse response = new UserResponse();
		if (login.getUserName() != null && !login.getUserName().isEmpty() && login.getPassword() != null
				&& !login.getPassword().isEmpty()) {
			Optional<UserEntity> findByEmail = userRepo.findByEmail(login.getUserName());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if (findByEmail.isEmpty()) {
				throw new NotFoundException("User not found. Please sign up.");
			} else if (!findByEmail.get().getIsActive()) {
				throw new InActiveException("User account is inactive. Please contact support.");
			} else if (passwordEncoder.matches(login.getPassword(), findByEmail.get().getPassword())) {
				BeanUtils.copyProperties(findByEmail.get(), response);
				return response;
			} else {
				throw new InvalidException("Invalid credentials. Please try again.");
			}
		}
		throw new BadRequestException("Invalid input. Please provide correct data.");
	}

}
