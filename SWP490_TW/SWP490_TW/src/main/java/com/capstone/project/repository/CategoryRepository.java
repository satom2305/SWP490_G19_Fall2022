package com.capstone.project.repository;

import com.capstone.project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT c FROM Category c WHERE c.categoryName LIKE %:categoryName% ")
    List<Category> searchListCategoryByName(@Param("categoryName") String categoryName);
}
