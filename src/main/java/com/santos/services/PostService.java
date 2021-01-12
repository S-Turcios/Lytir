package com.santos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.models.Posts;
import com.santos.repos.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	public List<Posts> findAllPost() {
		return postRepository.findAll();
	}
	public Posts createPost(Posts post) {
		return postRepository.save(post);
	}
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	public PostService() {
		// TODO Auto-generated constructor stub
	}

}
