package com.quickscrim.repositories;

import com.quickscrim.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}