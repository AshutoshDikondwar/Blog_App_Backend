package com.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private Integer categoryId;

	@NotBlank(message = "Category title can not be empty")
	@Size(min = 4, message = "Category title must be min 4 characters")
	private String categoryTitle;

	@NotBlank(message = "Category description can not be empty")
	@Size(min = 4, message = "Category description can not be empty")
	private String categoryDescription;

}
