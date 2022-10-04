package com.capstone.project.repository;

import com.capstone.project.domain.Role;
import com.capstone.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

