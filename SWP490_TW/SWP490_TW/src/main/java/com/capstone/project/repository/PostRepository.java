package com.capstone.project.repository;

import com.capstone.project.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post ,Integer> {
}
