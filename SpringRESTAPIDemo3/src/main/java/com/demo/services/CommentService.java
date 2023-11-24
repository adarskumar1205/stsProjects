package com.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CommentDto;
import com.demo.entities.Comment;
import com.demo.entities.Post;
import com.demo.repositories.CommentRepository;
import com.demo.repositories.PostRepository;

@Service
public class CommentService {
	
		@Autowired
		private CommentRepository commentRepository;
		
		@Autowired
		private PostRepository postRepository;
		
		@Autowired
		private ModelMapper mapper;

	 	public CommentDto createComment(long postId, CommentDto commentDto) throws Exception {
	 		
	 		Comment comment = mapToEntity(commentDto);
	 		
	 		Post post = postRepository.findById(postId).orElseThrow(() -> new Exception("Post ID " + postId + " Not Found"));
	 		
	 		comment.setPost(post);
	 		
	 		Comment commentResponse =  commentRepository.save(comment);
	 		
	 		return mapToDto(commentResponse);
	 	}

	 	public List<CommentDto> getCommentsByPostId(long postId){
	 		List<CommentDto> comments = commentRepository.getAllByPostId(postId)
	 									.stream()
	 										.map(comment-> mapToDto(comment))
	 										.collect(Collectors.toList());
	 		
	    	return comments;
	    }

	 	public CommentDto getCommentById(Long postId, Long commentId) throws Exception {
	 		Optional<Comment> comment = commentRepository.getAllByIdAndPostId(commentId,postId);
	 		
	 		return comment.isPresent() ? mapToDto(comment.get()) : null;
	    }

	 	public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
	 		Optional<Comment> comment = commentRepository.getAllByIdAndPostId(commentId,postId);
	 		
	 		if (comment.isPresent()) {
	            Comment newComment = comment.get();
	            newComment.setName(commentRequest.getName());
	            newComment.setEmail(commentRequest.getEmail());
	            newComment.setBody(commentRequest.getBody());
	           
	            return mapToDto(commentRepository.save(newComment));
	        }
	       
	    	return null;
	    }

	 	public  void deleteComment(Long postId, Long commentId) {
	 		Optional<Comment> comment = commentRepository.getAllByIdAndPostId(commentId,postId);
	 		
	 		 if(comment.isPresent())
	 			 commentRepository.delete(comment.get()) ;
	 		
	    }
	 	
	 	private CommentDto mapToDto(Comment comment) {
	 		CommentDto commentDto = mapper.map(comment, CommentDto.class);
	 		
	 		return commentDto;
	 	}
	 	
	 	private Comment mapToEntity(CommentDto commentDto) {
	 		Comment comment = mapper.map(commentDto, Comment.class);
	 		
	 		return comment;
	 	}
}
