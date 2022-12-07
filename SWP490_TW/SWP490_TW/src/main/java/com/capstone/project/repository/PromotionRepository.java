package com.capstone.project.repository;

import com.capstone.project.domain.Product;
import com.capstone.project.domain.Promotion;
import com.capstone.project.response.PromotionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    Optional<Promotion> findByPromotionCode(String code);

    @Query(value = "SELECT p FROM Promotion p WHERE p.promotionCode LIKE %:promotionCode%")
    List<Promotion> searchListPromotionByCode(@Param("promotionCode") String promotionCode);
}
