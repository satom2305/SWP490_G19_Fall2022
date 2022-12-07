package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Post;
import com.capstone.project.domain.User;
import com.capstone.project.repository.PostRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.PostRequest;
import com.capstone.project.response.PostResponse;
import com.capstone.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public PostResponse getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        return mapper.map(post, PostResponse.class);
    }

    @Override
    public List<PostResponse> getALLPost() {
        return postRepository.findAll()
                .stream()
                .map(post -> mapper.map(post, PostResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse createPost(PostRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("User not found", 404));
        Post post = postRepository.save(Post.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .image_link_detail(request.getImage_link_detail())
                .date(request.getDate())
                .image_link_thumbnail(request.getImage_link_thumbnail())
                .build());
        postRepository.save(post);
        return mapper.map(post, PostResponse.class);
    }

    @Override
    public PostResponse updatePost(Integer id, PostRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException("User not found", 404));
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        post.setUser(user);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImage_link_detail(request.getImage_link_detail());
        post.setDate(request.getDate());
        post.setImage_link_thumbnail(request.getImage_link_thumbnail());
        postRepository.save(post);
        return mapper.map(post, PostResponse.class);
    }

    @Override
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException("Account not found", 404));
        postRepository.delete(post);
    }

    @Override
    public List<PostResponse> searchPostByTitle(String title) {
        return postRepository.searchPostByTitle(title)
                .stream()
                .map(post -> mapper.map(post, PostResponse.class))
                .collect(Collectors.toList());
    }
}
