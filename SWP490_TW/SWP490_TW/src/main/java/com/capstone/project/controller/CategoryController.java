package com.capstone.project.controller;

import com.capstone.project.request.CategoryRequest;
import com.capstone.project.response.ResponseObject;
import com.capstone.project.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, categoryService.findAllCategory()));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, categoryService.createCategory(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, categoryService.updateCategory(id, request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, categoryService.findCategoryById(id)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/searchCategoryByName/{categoryName}")
    public ResponseEntity<?> searchCategoryByName(@PathVariable("categoryName") String categoryName){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", true, categoryService.searchCategoryByName(categoryName))
        );
    }
}
