package com.blog.service;

import java.util.List;

import com.blog.payload.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUser(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);

}
