package com.capstone.project.service.impl;

import com.capstone.project.config.exception.AppException;
import com.capstone.project.domain.Category;
import com.capstone.project.domain.Product;
import com.capstone.project.domain.ProductImg;
import com.capstone.project.repository.CategoryRepository;
import com.capstone.project.repository.ProductImgRepository;
import com.capstone.project.repository.ProductRepository;
import com.capstone.project.request.ProductRequest;
import com.capstone.project.response.ProductResponse;
import com.capstone.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImgRepository productImgRepository;
    private final ModelMapper mapper;

    @Override
    public List<ProductResponse> getAllProductAvailable() {
        return productRepository.findProductAvailable()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAllProduct()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", 404));
        Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new AppException("Category not found", 404));
        List<ProductImg> imgs = productImgRepository.findByProduct(product);
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .originalPrice(product.getOriginalPrice())
                .salePercent(product.getSalePercent())
                .sellPrice(product.getSellPrice())
                .amount(product.getAmount())
                .createdDate(product.getCreatedDate())
                .category(category)
                .productStatus(product.getProductStatus())
                .mainImg(product.getMainImg())
                .productImgs(imgs)
                .build();
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException("Category not found", 404));
        Product product = productRepository.save(Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .originalPrice(request.getOriginalPrice())
                .sellPrice(request.getSellPrice())
                .salePercent(request.getSalePercent())
                .category(category)
                .amount(request.getAmount())
                .createdDate(request.getCreatedDate())
                .productStatus(request.getProductStatus())
                .mainImg(request.getMainImg())
                .build());
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", 404));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException("Category not found", 404));
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setOriginalPrice(request.getOriginalPrice());
        product.setSellPrice(request.getSellPrice());
        product.setSalePercent(request.getSalePercent());
        product.setCategory(category);
        product.setAmount(request.getAmount());
        product.setCreatedDate(request.getCreatedDate());
        product.setProductStatus(request.getProductStatus());
        product.setMainImg(request.getMainImg());
        productRepository.save(product);
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse disableProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException("Product not found", 404));
        product.setProductStatus(2);
        productRepository.save(product);
        return mapper.map(product, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getLastSixProducts() {
        return productRepository.findLastSixProducts()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchProductByName(String productName) {
        System.out.println(productName);
        return productRepository.searchListProductByName(productName)
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchAvaProductByName(String productName) {
        System.out.println(productName);
        return productRepository.searchListAvaProductByName(productName)
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProductAvailableASC() {
        return productRepository.findByOrderBySellPriceAsc()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ProductResponse> getAllProductAvailableDES() {
        return productRepository.findProductAvailableDES()
                .stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }


}
