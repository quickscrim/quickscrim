package com.quickscrim.repositories;

import com.quickscrim.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

//    List<Post>findByPostCategory();


}