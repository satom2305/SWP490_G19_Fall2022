package com.capstone.project.repository;

import com.capstone.project.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<UserStatus, Integer> {

}

