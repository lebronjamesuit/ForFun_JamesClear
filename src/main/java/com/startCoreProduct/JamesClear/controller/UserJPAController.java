package com.startCoreProduct.JamesClear.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.startCoreProduct.JamesClear.Exception.UserNotFoundException;
import com.startCoreProduct.JamesClear.JPA.UserRespository;
import com.startCoreProduct.JamesClear.bean.Post;
import com.startCoreProduct.JamesClear.bean.User;
import com.startCoreProduct.JamesClear.services.UserDaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/jpaUser")
public class UserJPAController {

	private UserDaoService userDaoService;
	
	private UserRespository userRespo;

	public UserJPAController(UserDaoService userDaoService, UserRespository usrRespository) {
		super();
		this.userDaoService = userDaoService;
		this.userRespo =  usrRespository;
	}
	
	@GetMapping(path = "/getAllUsers")
	public List<User> getAllUsers(){
		return userRespo.findAll();
	}
	
    // Hateoas includes EntityMovel and MVCLINKBuilder, imported some static method
	// Custom exception
	@GetMapping(path = "/getUserById/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") int id) {
		
		Optional<User> optionalUser = userRespo.findById(id);
		if(optionalUser.isEmpty()) 
			throw new UserNotFoundException("The system cound not found the user which has id " + id);
		
		User user = optionalUser.get(); 
		// Start HateOas
		EntityModel<User> entityModelUser = EntityModel.of(user);
 		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
 				WebMvcLinkBuilder.methodOn(UserJPAController.class).getAllUsers()
 				);
		entityModelUser.add(link.withRel("get-all-users"));
 		
	   
		link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserJPAController.class).deleteUser(id));
		entityModelUser.add(link.withRel("delete-user-by-id"));
		
		link  = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).createUserEntity(user));
		entityModelUser.add(link.withRel("create new user"));
		// End HateOas
		
		return entityModelUser;
	}
	
    
    // Rest validation
    // Response with URI 
    @PostMapping("/createUserEntity")
    public ResponseEntity<User> createUserEntity (@Valid @RequestBody User user) {
    	
    	 User returnUser =  userRespo.save(user);
    	 if(returnUser.equals(null)) 
    		 throw new UserNotFoundException("The system cound not save the user which has name " + user.getName());
    		 
    	
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").
        		buildAndExpand(returnUser.getId())
        		.toUri();
    	return  ResponseEntity.created(location).build();
    	
    }
    
	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
		System.out.println("id truyen vao la "+ id);		
		userRespo.deleteById(id);
		return  ResponseEntity.created(null).build();
		
	}
	
	@GetMapping("/getPostsByUserId/{id}/posts")
	public List<Post> getPostByUserId (@PathVariable("id") Integer id){
		Optional<User> optionalUser = userRespo.findById(id);
		if(optionalUser.isEmpty()) 
			throw new UserNotFoundException("The system cound not found the user which has id " + id);
		
		User user = optionalUser.get();
		return user.getPosts();
	}
	
	
}
