package com.hacettepe.clubinn.service.implementations;


import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.UserDto;
import com.hacettepe.clubinn.model.entity.ClubCategory;
import com.hacettepe.clubinn.model.entity.SubClub;
import com.hacettepe.clubinn.model.entity.User;
import com.hacettepe.clubinn.model.repository.ClubCategoryRepository;
import com.hacettepe.clubinn.service.ClubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ClubCategoryServiceImpl implements ClubCategoryService {

    @Autowired
    private ClubCategoryRepository clubCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public ClubCategoryDto getOne(Long id) {
        ClubCategory category= clubCategoryRepository.getOne(id);
        if(category==null){
            return null;
        }
        else{
            return modelMapper.map(category, ClubCategoryDto.class);
        }
    }





    @Override
    public List<ClubCategoryDto> getAll() {
        List<ClubCategory> clubcategories= clubCategoryRepository.findAll();
        if(clubcategories==null){
            return null;
        }
        else{
            return Arrays.asList(modelMapper.map(clubcategories, ClubCategoryDto[].class));
        }
    }


    @Override
    public Boolean delete(Long clubId) {
        log.warn("club category-delete servise geldi");
        try {

            ClubCategory clubcategory = clubCategoryRepository.getOne(clubId);
            if(clubcategory !=null){
                clubCategoryRepository.delete(clubcategory);
                return Boolean.TRUE;
            }
            else{
                return Boolean.FALSE;
            }
        }
        catch (Exception e){
            log.error(" Silme sirasında bir sorun meydana geldi. Exception=", e);
            return Boolean.FALSE;
        }

    }




    @Override
    public Boolean createNewSubClub(ClubCategoryDto clubCategoryDto) {
        ClubCategory clubCategory = modelMapper.map(clubCategoryDto, ClubCategory.class);
        clubCategoryRepository.save(clubCategory);
        return true;
    }

    @Override
    public ClubCategoryDto updateSubClub(ClubCategoryDto clubCategoryDto) {
        return null;
    }



}
