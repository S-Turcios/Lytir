package com.santos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santos.models.Posts;
import com.santos.models.Comments;
import com.santos.repos.CommentRepository;

@Service
public class CommentServices {
	@Autowired
	private CommentRepository commentRepository;
	
	public Comments createComment(Comments comment) {
		return commentRepository.save(comment);
	}
	public void deleteReply(Long id) {
		commentRepository.deleteById(id);
	}
	public CommentServices() {
		// TODO Auto-generated constructor stub
	}
	public List<Comments> findAll() {
		return commentRepository.findAll();
	}

}
