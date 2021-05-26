package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.ClubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubCategoryRepository extends JpaRepository<ClubCategory, Long> {

    void deleteByClubCategoryName(String name);

    Boolean existsByClubCategoryName(String name);

}
