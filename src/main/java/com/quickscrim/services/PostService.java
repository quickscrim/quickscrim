package com.quickscrim.services;

import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postDao;

    public PostService(PostRepository postDao) {
        this.postDao = postDao;
    }

    public Iterable<Post> getAllPosts() {
        return postDao.findAll();
    }

    public Post getPost(long id) {
        return postDao.findOne(id);
    }

    public Post save(Post post) {
        return postDao.save(post);
    }

    public void delete(Long id) {
        postDao.delete(id);
    }
}
