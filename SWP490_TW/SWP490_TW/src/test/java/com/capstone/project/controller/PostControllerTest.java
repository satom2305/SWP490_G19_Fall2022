package com.capstone.project.controller;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Post;
import com.capstone.project.domain.User;
import com.capstone.project.repository.PostRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.PostRequest;
import com.capstone.project.response.PostResponse;
import com.capstone.project.service.impl.PostServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostControllerTest {
    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    private ModelMapper mapper;

    private Post post;

    private User user;

    @BeforeEach
    public void init() {
        post = new Post(1, user, "title", "content", "String", null, "String");
        user = new User(1, "admin", "admin", "admin@gmail.com", true, null, null, null);
    }




    @Test
    @DisplayName("Test get all post")
    public void TestGetAllPost() {
        List<Post> postList = new ArrayList<>();
        postList.add(post);
        when(postRepository.findAll()).thenReturn(postList);

        List<PostResponse> actual = postService.getALLPost();
        assertEquals(postList.size(), actual.size());
    }

    @Test
    @DisplayName("Test get all post fail")
    public void TestGetAllProductFail() {
        List<Post> expect = new ArrayList<>();
        expect.add(post);
        List<Post> actual = new ArrayList<>();
        actual.add(post);

        Mockito.when(postRepository.findAll()).thenThrow(new NullPointerException(""));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> postService.getALLPost());
        assertEquals("", exception.getMessage());
    }

    @Test
    @DisplayName("test update post success")
    public void TestUpdatePostSuccess() {
        PostRequest postRequest = new PostRequest(1, 1, "title", "content", "String", null, "String");
        Integer id = 1;
        Mockito.when(userRepository.findById(postRequest.getUserId())).thenReturn(Optional.ofNullable(user));
        Mockito.when(postRepository.findById(id)).thenReturn(Optional.ofNullable(post));

        PostResponse postResponse = postService.updatePost(id,postRequest);

        Assert.assertEquals(postRequest.getTitle(), postResponse.getTitle());
    }


    @Test
    @DisplayName("test update post fail")
    public void TestUpdatePostFail() {
        PostRequest postRequest = new PostRequest(1, 1, "title", "content", "String", null, "String");
        Integer id = 2;
        Mockito.when(userRepository.findById(postRequest.getUserId())).thenReturn(Optional.ofNullable(user));
        Mockito.when(postRepository.findById(id)).thenThrow(new AppException("Post not found", 404));

        AppException ex = Assert.assertThrows(AppException.class, () -> postService.updatePost(id, postRequest) );
        Assert.assertEquals("Post not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("test find post success")
    public void TestFindPost() {
        PostRequest postRequest = new PostRequest(1, 1, "title", "content", "String", null, "String");
        Integer id = 1;
        Mockito.when(postRepository.findById(id)).thenReturn(Optional.ofNullable(post));
        PostResponse postResponse = postService.getPostById(id);
        Assert.assertEquals(postRequest.getTitle(), postResponse.getTitle());
    }

    @Test
    @DisplayName("test find post fail")
    public void TestFindPostFail() {
        PostRequest postRequest = new PostRequest(1, 1, "title", "content", "String", null, "String");
        Integer id = 2;
        Mockito.when(postRepository.findById(id)).thenThrow(new AppException("Post not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> postService.getPostById(postRequest.getPostId()));
        Assert.assertEquals("Account not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

    @Test
    @DisplayName("test delete post")
    public void TestDelete() {
        PostRequest postRequest = new PostRequest(1, 1, "title", "content", "String", null, "String");
        Integer id = 1;
        Mockito.when(postRepository.findById(postRequest.getPostId())).thenReturn(Optional.ofNullable(post));
        postService.deletePost(id);
    }

    @Test
    @DisplayName("test delete post Fail")
    public void TestDeleteFail() {
        PostRequest postRequest = new PostRequest(1, 1, "title", "content", "String", null, "String");
        Integer id = 2;
        Mockito.when(postRepository.findById(id)).thenThrow(new AppException("Post not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> postService.deletePost(postRequest.getPostId()));
        Assert.assertEquals("Account not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }

}
