package com.startCoreProduct.JamesClear.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

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
import com.startCoreProduct.JamesClear.bean.User;
import com.startCoreProduct.JamesClear.services.UserDaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/notuse")
public class UserHateoasController {

	private UserDaoService userDaoService;

	public UserHateoasController(UserDaoService userDaoService) {
		super();
		this.userDaoService = userDaoService;
	}
	
	@GetMapping(path = "/getAllUsers")
	public List<User> getAllUsers(){
		return userDaoService.findAll();
	}
	
    // Hateoas includes EntityMovel and MVCLINKBuilder, imported some static method
	// Custom exception
	@GetMapping(path = "/getUserById/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") int id) {
		User user = userDaoService.findUserById(id);

		if(user == null) {
			throw new UserNotFoundException("The system cound not found the user which has id " + id);
		}
		
		// Start HateOas
		EntityModel<User> entityModelUser = EntityModel.of(user);
 		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
 				WebMvcLinkBuilder.methodOn(UserHateoasController.class).getAllUsers()
 				);
		entityModelUser.add(link.withRel("get-all-users"));
 		
	   
		link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserHateoasController.class).deleteUser(user));
		entityModelUser.add(link.withRel("delete-user-by-id"));
		
		link  = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).createUserEntity(user));
		entityModelUser.add(link.withRel("create new user"));
		// End HateOas
		
		return entityModelUser;
	}
	

	// Not used
    @PostMapping("/createUser")
	public void createUser( @RequestBody User user ) {
    	 System.out.println(" @PostMapping(\"/createUser\")  " + user.getName());
    	 userDaoService.createUser(user);
	}
    
    // Rest validation
    // Response with URI 
    @PostMapping("/createUserEntity")
    public ResponseEntity<User> createUserEntity (@Valid @RequestBody User user) {
    	User newuser = userDaoService.createUser(user);
    	
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").
        		buildAndExpand(newuser.getId())
        		.toUri();
    	return  ResponseEntity.created(location).build();
    	
    }
    
	@DeleteMapping("/deleteUserById")
	public ResponseEntity<User> deleteUser(@Valid @RequestBody User user) {
		userDaoService.deleteUserById(user);
		return  ResponseEntity.created(null).build();
		
	}
		
}
