package com.santos.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.santos.models.Comments;

public interface CommentRepository extends CrudRepository<Comments, Long> {
	List<Comments> findAll();
}
