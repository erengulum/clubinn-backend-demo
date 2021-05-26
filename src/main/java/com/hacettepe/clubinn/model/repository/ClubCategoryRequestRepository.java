package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.ClubCategoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubCategoryRequestRepository extends JpaRepository<ClubCategoryRequest,Long> {

    ClubCategoryRequest findByClubCategoryName(String name);

    Boolean existsByClubCategoryName(String name);

}
