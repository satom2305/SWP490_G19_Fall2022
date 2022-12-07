package com.capstone.project.repository;

import com.capstone.project.domain.Post;
import com.capstone.project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PostRepository extends JpaRepository<Post ,Integer> {
    @Query(value = "SELECT p FROM Post p WHERE p.title LIKE %:title%")
    List<Post> searchPostByTitle(@Param("title") String title);
}
