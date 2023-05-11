package com.startCoreProduct.JamesClear.HelloService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

// I want Spring to manage this 
@Component
public class UserDaoService {

	// Example of don't deal with database h2. just static value
	private List<User> uss = new ArrayList<User>();
	
	private static int userId = 0;

	
	public UserDaoService() {
		// TODO Auto-generated method stub
		uss.add(new User(++userId, "James", LocalDate.now().minusYears(20)));
		uss.add(new User(++userId, "David", LocalDate.now().minusYears(20)));
		uss.add(new User(++userId, "Linder", LocalDate.now().minusYears(20)));

	};
	
	public List<User> findAll() {
		return uss;
	}

	public User findUserById(int id){
		// TODO Auto-generated method stub
 	
		Predicate<? super User>  predicate =  user -> user.getId() == id;
		User o = uss.stream().filter(predicate).findFirst().orElse(null);
		return o;
	}


	public User createUser(User user) {
		
		System.out.println(" MINH" + user.getName());
			
		CharSequence birth = user.getBirthDay().toString();
		LocalDate date = LocalDate.parse(birth, DateTimeFormatter.ISO_LOCAL_DATE);
		
		User addUser = new User(++userId, user.getName(),date) ;
		uss.add(addUser);
		
		return addUser;
	}

	public void deleteUserById(User u) {
	
		Predicate<? super User> predicate = user -> user.getId() == u.getId();
		uss.removeIf(predicate);
		
	}
	
}
