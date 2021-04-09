package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String roleName);
}
