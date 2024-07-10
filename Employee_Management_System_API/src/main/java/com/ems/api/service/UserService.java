package com.ems.api.service;

import com.ems.api.dto.LoginForm;
import com.ems.api.dto.UserResponse;

public interface UserService {

	public UserResponse login(LoginForm login);

}
