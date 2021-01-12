package com.santos.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.santos.models.Posts;

public interface PostRepository extends CrudRepository<Posts, Long> {
	List<Posts> findAll();
}
