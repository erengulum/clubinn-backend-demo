package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.ClubCategoryRequestDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.entity.ClubCategoryRequest;

import java.util.List;

public interface ClubCategoryService {

    List<ClubCategoryDto> getAll();

    Boolean delete(Long clubId);

    String createNewClub(ClubCategoryDto clubCategoryDto);

    ClubCategoryDto updateSubClub(ClubCategoryDto clubCategoryDto);

    ClubCategoryDto getOne(Long id);

    String requestClubCategory(ClubCategoryRequestDto clubCategoryRequestDto);

    Boolean voteForRequest(Long requestId);

    List<ClubCategoryRequestDto> getAllRequests();

    Boolean deleteClubRequest(Long requestId);

    String convertRequestToClub(ClubCategoryRequestDto clubCategoryRequestDto);

}
