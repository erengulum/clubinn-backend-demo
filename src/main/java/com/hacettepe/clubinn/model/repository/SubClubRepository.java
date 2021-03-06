package com.hacettepe.clubinn.model.repository;

import com.hacettepe.clubinn.model.entity.SubClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubClubRepository extends JpaRepository<SubClub, Long> {

    List<SubClub> getAllByClubCategory_Id(Long categoryId);

    List<SubClub> getAllByForm_FormId(Long formId);

    SubClub findBySubClubName(String sclubname);

}
