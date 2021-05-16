package com.hacettepe.clubinn.service.implementations;

import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.entity.Chat;
import com.hacettepe.clubinn.model.entity.ClubCategory;
import com.hacettepe.clubinn.model.entity.SubClub;
import com.hacettepe.clubinn.model.repository.ClubCategoryRepository;
import com.hacettepe.clubinn.model.repository.SubClubRepository;
import com.hacettepe.clubinn.service.SubClubService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class SubClubServiceImpl implements SubClubService {

    private final ModelMapper modelMapper;
    private final SubClubRepository subClubRepository;
    private final ClubCategoryRepository clubCategoryRepository;
    private final ChatServiceImpl chatService;

    public SubClubServiceImpl(ModelMapper modelMapper, SubClubRepository subClubRepository, ClubCategoryRepository clubCategoryRepository, ChatServiceImpl chatService) {
        this.modelMapper = modelMapper;
        this.subClubRepository = subClubRepository;
        this.clubCategoryRepository = clubCategoryRepository;
        this.chatService = chatService;
    }

    @Override
    public SubClubDto getOne(Long id) {

        SubClub subClub = subClubRepository.getOne(id);

        if (subClub == null) {
            return null;
        } else {
            return modelMapper.map(subClub, SubClubDto.class);
        }
    }

    @Override
    public List<SubClubDto> getAll() {

        List<SubClub> subClubList = subClubRepository.findAll();

        if (subClubList == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(subClubList, SubClubDto[].class));
        }
    }

    @Override
    public List<SubClubDto> getAllByCategory(Long categoryId) {

        List<SubClub> subClubList = subClubRepository.getAllByClubCategory_Id(categoryId);
        if (subClubList == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(subClubList, SubClubDto[].class));
        }
    }

    @Override
    public Boolean deleteSubClub(Long id) {

        SubClub subclub = subClubRepository.getOne(id);
        chatService.deleteChat(subclub);

        if (subclub != null) {
            subClubRepository.delete(subclub);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    @Override
    public Boolean createNewSubClub(SubClubDto subClubDto) {
        try {
            SubClub subclub = subClubRepository.findBySubClubName(subClubDto.getSubClubName());
            if (subclub != null) {
                log.error("Ayni isimde bir subclub zaten var");
                return Boolean.FALSE;
            } else {
                SubClub sclub = new SubClub();
                sclub.setSubClubName(subClubDto.getSubClubName());
                sclub.setDescription(subClubDto.getDescription());
                ClubCategory clubCategory = clubCategoryRepository.getOne(subClubDto.getClubCategoryId());
                sclub.setClubCategory(clubCategory);

                Chat chat = chatService.createChat(sclub);
                sclub.setChat(chat);

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
