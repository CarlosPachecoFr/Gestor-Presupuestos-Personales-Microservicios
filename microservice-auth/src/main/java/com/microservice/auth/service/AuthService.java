package com.microservice.auth.service;

import com.microservice.auth.dto.LoginRequest;
import com.microservice.auth.dto.RegisterRequest;
import com.microservice.auth.rest.TokenResponse;

public interface AuthService {
	
	public TokenResponse registro(RegisterRequest request);
	
	public TokenResponse login(LoginRequest request);
}
