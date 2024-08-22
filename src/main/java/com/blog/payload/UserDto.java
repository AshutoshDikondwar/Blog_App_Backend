package com.blog.payload;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blog.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;

	@NotBlank(message = "Name cannot be empty")
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;

	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Password cannot be empty")
	@Size(min = 4,  message = "Password must be min of 4 characters")
	private String password;

	@NotBlank(message = "About cannot be empty")
	private String about;
	
	private List<CommentDto> comment = new ArrayList<>();
	
	private Set<Role> roles = new HashSet<>();
}
