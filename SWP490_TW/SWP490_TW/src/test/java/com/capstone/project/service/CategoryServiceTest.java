package com.capstone.project.service;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Category;
import com.capstone.project.repository.CategoryRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.CategoryRequest;
import com.capstone.project.response.CategoryResponse;
import com.capstone.project.service.impl.CategoryServiceImpl;
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

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Spy
    private ModelMapper mapper;

    private Category category;

    @BeforeEach
    public  void init (){
        category =  new Category(1, "test category");
    }
    @Test
    public void TestCreateCategory() {
        //set up
        CategoryRequest categoryRequest = new CategoryRequest("test category");

        Mockito.when(categoryRepository.save(Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build())).thenReturn(category);

        CategoryResponse actual = categoryService.create(categoryRequest);

        Mockito.verify(mapper, Mockito.timeout(1)).map(category, CategoryResponse.class);

        Assert.assertEquals(category.getCategoryId(), actual.getCategoryId());
        Assert.assertEquals(category.getCategoryName(), actual.getCategoryName());

    }

    /**
     *
     *
     * **/
    @Test
    @DisplayName("test create category fail when category is null ")
    public void TestCreateCategoryFail() {
        //set up
        Category category = new Category();
        CategoryRequest categoryRequest = new CategoryRequest("test category");

        Mockito.when(categoryRepository.save(category)).thenReturn(category);

        //execute
        Exception ex = Assert.assertThrows(Exception.class, () -> categoryService.create(categoryRequest) );

        //verify
        Assert.assertEquals("source cannot be null", ex.getMessage());

    }

    @Test
    @DisplayName("test update category fail when category not found")
    public void TestUpdateCategoryFail() {
        //set up
        CategoryRequest categoryRequest = new CategoryRequest("test category");
        Integer id = 2;
        Mockito.when(categoryRepository.findById(id)).thenThrow(new AppException("Category not found", 404));

        AppException ex = Assert.assertThrows(AppException.class, () -> categoryService.update(id, categoryRequest) );

        Assert.assertEquals("Category not found", ex.getMessage());
        Assert.assertEquals(404, ex.getErrorCode());


    }

    @Test
    @DisplayName("test update category success")
    public void TestUpdateCategorySuccess() {
        //set up
        CategoryRequest categoryRequest = new CategoryRequest("hello");
        Integer id = 1;
        Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.ofNullable(category));

        CategoryResponse categoryResponse = categoryService.update(id, categoryRequest);

        Assert.assertEquals("hello", categoryResponse.getCategoryName());


    }


}
