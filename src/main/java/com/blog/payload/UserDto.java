package com.blog.payload;

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
	@Size(min = 4, max = 10, message = "Password must be min of 4 characters and max of 10 characters")
	private String password;

	@NotBlank(message = "About cannot be empty")
	private String about;
}
