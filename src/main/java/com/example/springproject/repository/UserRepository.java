package com.example.springproject.repository;

//import com.example.springproject.model.Role;

import com.example.springproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    Role findRoleByName(String roleUser);
}
