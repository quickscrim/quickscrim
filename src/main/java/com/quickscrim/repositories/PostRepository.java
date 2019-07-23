package com.quickscrim.repositories;

import com.quickscrim.models.Category;
import com.quickscrim.models.Post;
import com.quickscrim.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByPostByUser(User user);
    List<Post> findByBodyIsLikeOrTitleIsLike(String term, String term2);
//    List<Post> findAllByPostByCategory (Category category);
}