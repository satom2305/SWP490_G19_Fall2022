package com.capstone.project.service;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Category;
import com.capstone.project.domain.Product;
import com.capstone.project.repository.CategoryRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.ProductRequest;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.service.impl.ProductServiceImpl;
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
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Spy
    private ModelMapper mapper;

    private Product product;

    private Category category;

    @BeforeEach
    public void init() {
        product = new Product(1, "Test product", "Test description", 10, 10, 10, 10, null, 1, "test img", category, null, null);
        category = new Category(1, "test category");
    }


    @Test
    public void TestGetAllProduct() {
        List<Product> productList = List.of(product);
        when(productRepository.findAllProduct()).thenReturn(productList);

        List<ProductResponse> actual = productService.getAllProduct();
        assertEquals(productList.size(), actual.size());
    }

    @Test
    public void TestGetAllProductFail(){
        List<Product> expect  = new ArrayList<>();
        expect.add(product);
        List<Product> actual = new ArrayList<>();
        actual.add(product);

        Mockito.when(productRepository.findAllProduct()).thenThrow(new NullPointerException(""));
        NullPointerException exception = assertThrows(NullPointerException.class, () -> productService.getAllProduct());
        assertEquals("",exception.getMessage());
    }

    @Test
    @DisplayName("test update product success")
    public void TestUpdateProductSuccess() {
        //set up
        ProductRequest productRequest = new ProductRequest(1, "Test product", "Test description", 10, 10, 10, 10, null, 1,1, "test img");

        Integer id = 1;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        Mockito.when(categoryRepository.findById(productRequest.getCategoryId())).thenReturn(Optional.ofNullable(category));
        category = new Category(1, "test category");
        ProductResponse productResponse = productService.update(id, productRequest);

        Assert.assertEquals("Test product", productResponse.getProductName());


    }

    @Test
    @DisplayName("test update product fail")
    public void TestUpdateProductFail() {
        //set up
        ProductRequest productRequest = new ProductRequest( 1,"Test product", "Test description", 10, 10, 10, 10, null, 1,1, "test img");

        Integer id = 2;
        Mockito.when(productRepository.findById(id)).thenThrow(new AppException("Product not found", 404));
        Mockito.when(categoryRepository.findById(productRequest.getCategoryId())).thenReturn(Optional.ofNullable(category));
        AppException ex = Assert.assertThrows(AppException.class, () -> productService.update(id, productRequest) );
        Assert.assertEquals("Product not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }


    @Test
    @DisplayName("test find product success")
    public void TestFindProduct() {
        ProductRequest productRequest = new ProductRequest( 1,"Test product", "Test description", 10, 10, 10, 10, null, 1,1, "test img");
        Integer id = 1;
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.ofNullable(product));
        ProductResponse productResponse = productService.findById(productRequest.getProductId());
        Assert.assertEquals("Test product", productResponse.getProductName());
    }

    @Test
    @DisplayName("test find product fail")
    public void TestFindProductFail() {
        ProductRequest productRequest = new ProductRequest( 1,"Test product", "Test description", 10, 10, 10, 10, null, 1,1, "test img");
        Integer id = 2;
        Mockito.when(productRepository.findById(id)).thenThrow(new AppException("Product not found", 404));
        AppException ex = Assert.assertThrows(AppException.class, () -> productService.findById(productRequest.getProductId()));
        Assert.assertEquals("Product not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());
    }
}
