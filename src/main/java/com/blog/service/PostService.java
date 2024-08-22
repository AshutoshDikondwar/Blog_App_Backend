package com.blog.service;

import java.util.List;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {

//	CREATE
	PostDto createPost(PostDto postdto, Integer userId, Integer categoryId);

//	UPDATE
	PostDto updatePost(PostDto postDto, Integer postId);

//	DELETE
	void deletePost(Integer posteId);

//	GET SINGLE POST
	PostDto getSinglePost(Integer postId);

//	GET ALL POSTS
	PostResponse getAllPosts(Integer pageNum, Integer pageSize, String sortBy, String sortDir);

//	GET POST BY CATEGORY
	PostResponse getAllPostsByCategory(Integer categoryId, Integer pageNum, Integer pageSIze,  String sortBy, String sortDir);

//	GET ALL POSTS BY USER
	PostResponse getAllPostsbyUser(Integer userId, Integer pageNum, Integer pageSIze, String sortBy, String sortDir);

//	GET POSTS BY SEARCH
	List<PostDto> searchPosts(String keyword);
	
}
