package com.startCoreProduct.JamesClear.HelloController;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.startCoreProduct.JamesClear.HelloBean.HelloBean;
import com.startCoreProduct.JamesClear.HelloService.StudentsDaoService;
import com.startCoreProduct.JamesClear.HelloService.User;
import com.startCoreProduct.JamesClear.HelloService.UserDaoService;

@RestController
public class HelloController {
	
	private MessageSource messageSource ;
	
	public HelloController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(method = RequestMethod.GET, path ="/hello")
	public String sayHello () {
		return "this is a return string of method sayHello ";
	}
	
	// This way should be embrace
	@GetMapping(path = "/hello2")
	public String sayHelloReableCode() {
		return "reable way to request a hello. method is sayHelloReableCode";
	}
	
	@GetMapping(path= "/helloBean")
	public HelloBean  sayHelloBean() {
		return new HelloBean("this is a message from a bean");
	}
	
	// Example static method
	@GetMapping(path = "/getAllStudent")
	public List<User> getAllStudent(){

		return StudentsDaoService.findAll();
	}
	
	@GetMapping("/helloInternational")
	public String helloInternational() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("hello.message", null, "this is default message", locale);
	}
	
	
	

}
