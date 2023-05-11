package com.startCoreProduct.JamesClear.Exception;

import java.time.LocalDateTime;


public class ErrorMessage {

	private LocalDateTime localdatetime;
	private String message;
	private String details;
	private String helloMinh;
	public ErrorMessage(LocalDateTime localdatetime, String message, String details) {
		super();
		this.localdatetime = localdatetime;
		this.message = message;
		this.details = details;
	}
	public void setLocaldatetime(LocalDateTime localdatetime) {
		this.localdatetime = localdatetime;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setHelloMinh(String helloMinh) {
		this.helloMinh = helloMinh;
	}
	public LocalDateTime getLocaldatetime() {
		return localdatetime;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public String getHelloMinh() {
		return helloMinh;
	} 
	
	
}
