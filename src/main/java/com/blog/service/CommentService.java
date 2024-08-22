package com.blog.service;

import com.blog.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId);

	void deleteCOmment(Integer commentId);

}
