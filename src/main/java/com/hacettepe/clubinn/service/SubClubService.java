package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.AnnouncementDto;
import com.hacettepe.clubinn.model.dto.JoinDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.dto.UserDto;


import java.util.List;

public interface SubClubService {

    SubClubDto getOne(Long id);

    List<SubClubDto> getAll();

    Boolean deleteSubClub(Long id);

    Boolean createNewSubClub(SubClubDto subClubDto);

    Boolean join(JoinDto joinDto);

    List<SubClubDto> getAllByCategory(Long categoryId);

    Boolean updateSubClub(SubClubDto subClubDto);

    List<SubClubDto> getAllSubClubMemberships(String username);

    List<UserDto> getAllSubclubMembers(Long subclubId);

    Boolean assignAdmin(Long subclubId, Long userId);

    UserDto getSubclubAdmin(Long subclubId);

    Boolean createNewAnnouncement(AnnouncementDto announcementDto, Long subclubId);

    Boolean deleteSubClubAnnouncement(Long annoncementId);

    List<AnnouncementDto> getAllAnnouncements(Long subclubId);

    List<SubClubDto> getAllByFormId(Long formId);

}
