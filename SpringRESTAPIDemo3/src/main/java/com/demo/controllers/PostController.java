package com.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.CommentDto;
import com.demo.dto.PostDto;
import com.demo.dto.PostResponse;
import com.demo.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
    private PostService postService;

	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	@PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
    }
	
	@GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable long postId) throws Exception{
        return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.FOUND);
    }
	
	@GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title",  required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable long postId) throws Exception{
        return new ResponseEntity<PostDto>(postService.updatePost(postDto,postId), HttpStatus.OK);
    }
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) throws Exception{
		postService.deletePostById(postId);
        return new ResponseEntity<String>("Post entity deleted successfully.",HttpStatus.OK);
    }
	
	@GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId){
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}