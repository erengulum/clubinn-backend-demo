package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    List<User> getAllByRoleName(String role);

    User findByEmail(String email);


}
