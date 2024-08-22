package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payload.UserDto;
import com.blog.repositories.RoleRepository;
import com.blog.repositories.UserRepo;
import com.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo useRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository rolerepo;

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.rolerepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newuser = this.useRepo.save(user);

		return this.modelMapper.map(newuser, UserDto.class);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);

		User persistedUser = useRepo.save(user);

		return this.userToDto(persistedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.useRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User persistedUser = this.useRepo.save(user);
		return this.userToDto(persistedUser);

	}

	@Override
	public UserDto getUser(Integer userId) {

		User user = this.useRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.useRepo.findAll();
		if (users.isEmpty()) {
			throw new ResourceNotFoundException("No User Found");
		}
		List<UserDto> usersDtoList = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return usersDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.useRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
		this.useRepo.delete(user);

	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
