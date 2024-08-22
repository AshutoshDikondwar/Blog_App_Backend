package com.blog.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

//	Commence method will be called when unauthorized user tries to access authorized request
//	commence method: You might implement this method to send a 401 Unauthorized response with a specific error message indicating that a valid JWT token is required
//	This method does not perform the authentication check itself but responds to the failure by, typically, returning a 401 Unauthorized error to the client.
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
//		if anyone tries to access request without token
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}

}
