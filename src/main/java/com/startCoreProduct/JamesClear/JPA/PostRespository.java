package com.startCoreProduct.JamesClear.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import com.startCoreProduct.JamesClear.PostService.Post;

public interface PostRespository extends JpaRepository<Post, Long> {

}
