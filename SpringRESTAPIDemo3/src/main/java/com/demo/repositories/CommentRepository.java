package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> getAllByPostId(long postId);
	List<Comment> findByPostId(long postId);
	Optional<Comment> getAllByIdAndPostId(Long commentId, Long postId);
}
