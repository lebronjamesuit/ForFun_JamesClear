package com.startCoreProduct.JamesClear.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.startCoreProduct.JamesClear.HelloService.User;

public interface UserRespository extends JpaRepository<User, Integer> {


}
