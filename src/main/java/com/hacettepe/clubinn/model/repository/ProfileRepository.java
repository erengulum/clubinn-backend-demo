package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.Profile;
import com.hacettepe.clubinn.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Profile findByUserUsername(String username);
}

