package com.capstone.project.repository;

import com.capstone.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Modifying
    @Query(value = "Insert into authorities (user_id,role_id) values (:userId,:roleId)",nativeQuery = true)
    @Transactional
    void setRole(@Param("userId") Integer userId,@Param("roleId") Integer id);
}
