package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.SubClubDto;


import java.util.List;

public interface SubClubService {

    SubClubDto getOne(Long id);

    List<SubClubDto> getAll();

    Boolean deleteSubClub(Long id);

    Boolean createNewSubClub(SubClubDto subClubDto);



    List<SubClubDto> getAllByCategory(Long categoryId);

    Boolean updateSubClub(SubClubDto subClubDto);


}
