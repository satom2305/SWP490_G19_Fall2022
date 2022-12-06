package com.capstone.project.controller;

import com.capstone.project.domain.Cart;
import com.capstone.project.domain.Category;
import com.capstone.project.domain.Product;
import com.capstone.project.domain.User;
import com.capstone.project.repository.CartRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.repository.UserRepository;
import com.capstone.project.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CartControllerTest {
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
        product = new Product(1, "Test product", "Test description", 10.0, 10.0, 10.0, 10, null, 1, "test img", category, null, null);
        user = new User(1, "admin", "admin", "admin@gmail.com", true, null, null, null);
        cart = new Cart(1,product,10,user);
    }
}
