package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;

import java.util.List;

public interface ClubCategoryService {

    List<ClubCategoryDto> getAll();

    Boolean delete(Long clubId);

    Boolean createNewSubClub(ClubCategoryDto clubCategoryDto);

    ClubCategoryDto updateSubClub(ClubCategoryDto clubCategoryDto);

    ClubCategoryDto getOne(Long id);
}
