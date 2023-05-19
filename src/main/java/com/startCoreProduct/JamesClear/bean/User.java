package com.startCoreProduct.JamesClear.bean;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Generated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {
  
    User(){}
  	
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min = 2, message = "Name must more than 2 characters")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "Birthday must be in the past")
	@JsonProperty("birth_day")
	private LocalDate birthDay;
	
	
	@OneToMany (mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore // posts are include in the response json.
	private List<Post> posts;
	
	public User(int id, @Size(min = 2, message = "Name must more than 2 characters") String name,
			@Past(message = "Birthday must be in the past") LocalDate birthDay) {
		super();
		this.id = id;
		this.name = name;
		this.birthDay = birthDay;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthDay == null) {
			if (other.birthDay != null)
				return false;
		} else if (!birthDay.equals(other.birthDay))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (posts == null) {
			if (other.posts != null)
				return false;
		} else if (!posts.equals(other.posts))
			return false;
		return true;
	}
	
	
	
}
