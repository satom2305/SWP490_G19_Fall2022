package com.capstone.project.service;

import com.capstone.project.domain.Cart;
import com.capstone.project.domain.Category;
import com.capstone.project.domain.Product;
import com.capstone.project.domain.User;
import com.capstone.project.repository.CartRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.request.CartRequest;
import com.capstone.project.request.ProductRequest;
import com.capstone.project.response.CartResponse;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.service.impl.CartServiceImpl;
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
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartServiceTest {
    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    private ModelMapper mapper;

    private Product product;
    private User user;
    private Cart cart;
    private Category category;

    @BeforeEach
    public void init() {
        category = new Category(1, "test category");
        product = new Product(1, "Test product", "Test description", 10, 10, 10, 10, null, 1, "test img", category, null, null);
        user = new User(1, "admin", "admin", "admin@gmail.com", true, null, null, null);
        cart = new Cart(1,product,10,user);
    }
}
