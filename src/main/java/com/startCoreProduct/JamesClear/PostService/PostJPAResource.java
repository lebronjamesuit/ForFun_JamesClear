package com.startCoreProduct.JamesClear.PostService;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.startCoreProduct.JamesClear.HelloService.User;
import com.startCoreProduct.JamesClear.HelloService.UserNotFoundException;
import com.startCoreProduct.JamesClear.JPA.PostRespository;
import com.startCoreProduct.JamesClear.JPA.UserRespository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostJPAResource {

	private PostRespository postRespo;
	
	private UserRespository userRespo;
	
	public PostJPAResource(PostRespository postRespo, UserRespository userRespo) {
		super();
		this.postRespo = postRespo;
		this.userRespo = userRespo;
	}

	// Rest validation
    // Response with URI 
    @PostMapping("/createPostEntity/{userId}")
    public ResponseEntity<User> createPostEntity (@PathVariable("userId") int userId, @Valid @RequestBody Post post) {
    	
    	 Optional<User> optionalUser = userRespo.findById(userId);
		 if(optionalUser.isEmpty()) 
			throw new UserNotFoundException("The system cound not found the user which has id " + userId);
		
		 User user = optionalUser.get();
    	 post.setUser(user);
    	 
    	 Post returnPost =  postRespo.save(post);
    	 if(returnPost.equals(null)) 
    		 throw new UserNotFoundException("The system cound not save the post which has name " + post.getDescription());
    		 
    	
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/{id}").
        		buildAndExpand(returnPost.getId())
        		.toUri();
    	return  ResponseEntity.created(location).build();
    	
    }
}
