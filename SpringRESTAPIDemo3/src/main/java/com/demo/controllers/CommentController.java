package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CommentDto;
import com.demo.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}")
	public ResponseEntity<CommentDto> createComment(@PathVariable long postId,
													@RequestBody CommentDto commentDto) throws Exception{
		
		return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable long postId) throws Exception{
		
		return new ResponseEntity<List<CommentDto>>(commentService.getCommentsByPostId(postId), HttpStatus.FOUND);
	}
	
	@GetMapping("/posts/{postId}/{commentId}")
	public ResponseEntity<CommentDto> getComment(@PathVariable long postId
												,@PathVariable long commentId) throws Exception{
		
		return new ResponseEntity<CommentDto>(commentService.getCommentById(postId,commentId), HttpStatus.FOUND);
	}
	
	@PutMapping("/posts/{postId}/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,
													@PathVariable long commentId,
													@RequestBody CommentDto commentDto) throws Exception{
		
		return new ResponseEntity<CommentDto>(commentService.updateComment(postId,commentId,commentDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable long postId,@PathVariable long commentId) throws Exception{
		commentService.deleteComment(postId,commentId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
}