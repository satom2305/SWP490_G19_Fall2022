package com.capstone.project.service;

import com.capstone.project.request.PostRequest;
import com.capstone.project.response.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse getPostById(Integer id);

    List<PostResponse> getALLPost();

    PostResponse createPost(PostRequest request);

    PostResponse updatePost(Integer id, PostRequest request);

    void deletePost(Integer id);
}
