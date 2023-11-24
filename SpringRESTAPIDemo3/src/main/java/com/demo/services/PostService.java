package com.demo.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.dto.CommentDto;
import com.demo.dto.PostDto;
import com.demo.dto.PostResponse;
import com.demo.entities.Category;
import com.demo.entities.Comment;
import com.demo.entities.Post;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.PostRepository;

@Service
public class PostService {
	
		@Autowired
		private PostRepository postRepository;
		
		@Autowired
		private CategoryRepository categoryRepository;
		
		@Autowired
		private ModelMapper mapper;
		
	 	public PostService() {
			super();
		}

		public PostDto createPost(PostDto postDto) {
			
			Category category = categoryRepository.findById(postDto.getCategoryId())
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

	        // convert DTO to entity
	        Post post = mapToEntity(postDto);
	        post.setCategory(category);
	        Post newPost = postRepository.save(post);

	        // convert entity to DTO
	        PostDto postResponse = mapToDto(newPost);
	        return postResponse;
	 	}

		public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
	    	
	    	
			Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();

	        // create Pageable instance
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

	        Page<Post> posts = postRepository.findAll(pageable);

	        // get content for page object
	        List<Post> listOfPosts = posts.getContent();

	        List<PostDto> content= listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

	        PostResponse postResponse = new PostResponse();
	        postResponse.setContent(content);
	        postResponse.setPageNo(posts.getNumber());
	        postResponse.setPageSize(posts.getSize());
	        postResponse.setTotalElements(posts.getTotalElements());
	        postResponse.setTotalPages(posts.getTotalPages());
	        postResponse.setLast(posts.isLast());
	        
	        return postResponse;

	    }

		public PostDto getPostById(long id) throws Exception {
			Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
	        return mapToDto(post);
	    }

		public PostDto updatePost(PostDto postDto, long id) throws Exception {
			Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

	        Category category = categoryRepository.findById(postDto.getCategoryId())
	                        .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

	        post.setTitle(postDto.getTitle());
	        post.setDescription(postDto.getDescription());
	        post.setContent(postDto.getContent());
	        post.setCategory(category);
	        Post updatedPost = postRepository.save(post);
	        return mapToDto(updatedPost);
	    }

		public void deletePostById(long id) throws Exception {
			Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
	        postRepository.delete(post);
		
	    }
		
		public List<PostDto> getPostsByCategory(Long categoryId) {

	        Category category = categoryRepository.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

	        List<Post> posts = postRepository.findByCategoryId(categoryId);

	        return posts.stream().map((post) -> mapToDto(post))
	                .collect(Collectors.toList());
	    }
		
		private PostDto mapToDto(Post post) {
			
			//receive a post and map it to PostDto
			PostDto postDto = mapper.map(post, PostDto.class);
			
			//return postDto
			return postDto;
		}
		
		private Post mapToEntity(PostDto postDto) {
			
			//receive a postDto and map it to Post
			Post post = mapper.map(postDto, Post.class);
			
			//return post
			return post;
		}
		
		

	   
}
