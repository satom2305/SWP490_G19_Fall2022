package com.capstone.project.service;

import com.capstone.project.request.PostRequest;
import com.capstone.project.response.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse getPostById(Integer id);

    List<PostResponse> getALL();

    PostResponse create(PostRequest request);

    PostResponse update(Integer id, PostRequest request);

    void delete(Integer id);
}
