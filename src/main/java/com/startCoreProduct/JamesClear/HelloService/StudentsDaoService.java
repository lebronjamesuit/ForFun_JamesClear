package com.startCoreProduct.JamesClear.HelloService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// This is no @component anotation
public class StudentsDaoService {

	// Example of don't deal with database h2. just static value
	private static List<User> uss = new ArrayList<User>();

	static {
		// TODO Auto-generated method stub
		uss.add(new User(1, "students 1", LocalDate.now().minusYears(20)));
		uss.add(new User(2, "amx", LocalDate.now().minusYears(20)));
		uss.add(new User(3, "ssssss", LocalDate.now().minusYears(20)));

	};
	
	public static List<User> findAll() {
		return uss;
	}
}
