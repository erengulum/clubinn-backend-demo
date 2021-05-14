package com.hacettepe.clubinn.service.implementations;

import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.entity.ClubCategory;
import com.hacettepe.clubinn.model.entity.Role;
import com.hacettepe.clubinn.model.entity.SubClub;
import com.hacettepe.clubinn.model.entity.User;
import com.hacettepe.clubinn.model.repository.ClubCategoryRepository;
import com.hacettepe.clubinn.model.repository.SubClubRepository;
import com.hacettepe.clubinn.service.ClubCategoryService;
import com.hacettepe.clubinn.service.SubClubService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class SubClubServiceImpl implements SubClubService {



    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubClubRepository subClubRepository;

    @Autowired
    private ClubCategoryRepository clubCategoryRepository;

    @Override
    public SubClubDto getOne(Long id) {
        SubClub sclub= subClubRepository.getOne(id);
        if(sclub==null){
            return null;
        }
        else{
            return modelMapper.map(sclub, SubClubDto.class);
        }
    }




    @Override
    public List<SubClubDto> getAll() {
        List<SubClub> subclubs= subClubRepository.findAll();
        if(subclubs==null){
            return null;
        }
        else{
            return Arrays.asList(modelMapper.map(subclubs, SubClubDto[].class));
        }
    }


    @Override
    public List<SubClubDto> getAllByCategory(Long categoryId) {
        List<SubClub> subclubs= subClubRepository.getAllByClubCategory_Id(categoryId);
        if(subclubs==null){
            return null;
        }
        else{
            return Arrays.asList(modelMapper.map(subclubs, SubClubDto[].class));
        }
    }


    @Override
    public Boolean deleteSubClub(Long id) {
        SubClub subclub = subClubRepository.getOne(id);
        if(subclub !=null){
            subClubRepository.delete(subclub);
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }

    }

    @Override
    public Boolean createNewSubClub(SubClubDto subClubDto) {
        try {
            SubClub subclub = subClubRepository.findBySubClubName(subClubDto.getSubClubName());
            if(subclub!=null){
                log.error("Ayni isimde bir subclub zaten var");
                return Boolean.FALSE;
            }


            else{
                SubClub sclub = new SubClub();
                sclub.setSubClubName(subClubDto.getSubClubName());
                sclub.setDescription(subClubDto.getDescription());
                ClubCategory clubCategory = clubCategoryRepository.getOne(subClubDto.getClubCategoryId());
                sclub.setClubCategory(clubCategory);

                subClubRepository.save(sclub);
                return Boolean.TRUE;
            }


        } catch (Exception e) {
            log.error("Yeni subclub olusturulurken bir hata meydana geldi. ", e);
            return Boolean.FALSE;
        }



    }

    @Override
    public Boolean updateSubClub(SubClubDto subClubDto) {
        SubClub subclub = subClubRepository.findBySubClubName(subClubDto.getSubClubName());
        subclub.setSubClubName(subClubDto.getSubClubName());
        subclub.setDescription(subClubDto.getDescription());
        return true;
    }
}
