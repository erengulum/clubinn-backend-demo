package com.hacettepe.clubinn.service.implementations;

import com.hacettepe.clubinn.model.dto.*;
import com.hacettepe.clubinn.model.entity.*;
import com.hacettepe.clubinn.model.repository.*;
import com.hacettepe.clubinn.service.SubClubService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class SubClubServiceImpl implements SubClubService {

    private final ModelMapper modelMapper;
    private final SubClubRepository subClubRepository;
    private final ClubCategoryRepository clubCategoryRepository;
    private final ChatServiceImpl chatService;
    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final FeedbackRepository feedbackRepository;

    public SubClubServiceImpl(ModelMapper modelMapper, SubClubRepository subClubRepository,
                              ClubCategoryRepository clubCategoryRepository,
                              ChatServiceImpl chatService, UserRepository userRepository,
                              AnnouncementRepository announcementRepository, FeedbackRepository feedbackRepository) {
        this.modelMapper = modelMapper;
        this.subClubRepository = subClubRepository;
        this.clubCategoryRepository = clubCategoryRepository;
        this.chatService = chatService;
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Displays the sub club screen.
     *
     * @param id --> Sub Club ID
     * @return --> Sub club dto for frontend
     **/
    @Override
    public SubClubDto getOne(Long id) {

        SubClub subClub = subClubRepository.getOne(id);

        if (subClub == null) {
            return null;
        } else {
            return modelMapper.map(subClub, SubClubDto.class);
        }
    }

    /**
     * Displays all sub clubs screen.
     *
     * @return --> All sub club dto for frontend
     **/
    @Override
    public List<SubClubDto> getAll() {

        List<SubClub> subClubList = subClubRepository.findAll();

        if (subClubList == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(subClubList, SubClubDto[].class));
        }
    }

    /**
     * Shows all sub clubs that the user is not a member of.
     *
     * @return --> All sub club dto for frontend
     **/
    @Override
    public List<SubClubDto> getAllSubClubNotAMember() {
        List<SubClub> subClubList = subClubRepository.findAll();
        List<SubClub> notMemberSubClubList = new ArrayList<>();
        User user = getAuthenticatedUser();
        for (SubClub subClub : subClubList) {
            if (!subClub.getMembers().contains(user)) {
                notMemberSubClubList.add(subClub);
            }
        }
        if (notMemberSubClubList == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(notMemberSubClubList, SubClubDto[].class));
        }
    }

    /**
     * Displays all sub clubs by category.
     *
     * @param categoryId --> Sub club ID
     * @return --> All sub club dto for frontend
     **/
    @Override
    public List<SubClubDto> getAllByCategory(Long categoryId) {

        List<SubClub> subClubList = subClubRepository.getAllByClubCategory_Id(categoryId);
        if (subClubList == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(subClubList, SubClubDto[].class));
        }
    }

    /**
     * Provides sub club deletion from database.
     *
     * @param id --> Sub club ID
     * @return --> All sub club dto for frontend
     **/
    @Override
    public Boolean deleteSubClub(Long id) {

        SubClub subclub = subClubRepository.getOne(id);

        if (subclub != null) {
            subClubRepository.delete(subclub);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    /**
     * Adds a new sub club to the database.
     *
     * @param subClubDto --> Sub club dto from frontend
     * @return --> All sub club dto for frontend
     **/
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
                sclub.setImageurl(subClubDto.getImageurl());
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

    /**
     * It allows the user to login to the sub club.
     *
     * @param joinDto --> Sub club dto from frontend
     * @return --> Boolean message
     **/
    @Override
    public Boolean join(JoinDto joinDto) {
        SubClub subClub = subClubRepository.getOne(joinDto.getSubclubId());
        User user = userRepository.findByUsername(joinDto.getUsername());

        for (User member : subClub.getMembers()) {
            if (member == user)
                return Boolean.FALSE;
        }

        if (user == null || subClub == null)
            return Boolean.FALSE;

        subClub.getMembers().add(user);
        subClubRepository.save(subClub);
        user.getSubclubs().add(subClub);
        userRepository.save(user);
        log.warn("join basariyla gerceklestirildi");
        return Boolean.TRUE;
    }

    /**
     * It shows the sub club list according to the form id that comes as a parameter.
     *
     * @param formId --> Form ID
     * @return --> All sub club dto for frontend
     **/
    @Override
    public List<SubClubDto> getAllByFormId(Long formId) {

        List<SubClub> subClubList = subClubRepository.getAllByForm_FormId(formId);
        if (subClubList == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(subClubList, SubClubDto[].class));
        }
    }

    /**
     * Updates sub club content.
     *
     * @param subClubDto --> Sub Club to be updated
     * @return --> Boolean Message
     **/
    @Override
    public Boolean updateSubClub(SubClubDto subClubDto) {
        SubClub subclub = subClubRepository.findBySubClubName(subClubDto.getSubClubName());
        subclub.setSubClubName(subClubDto.getSubClubName());
        subclub.setDescription(subClubDto.getDescription());
        return true;
    }

    /**
     * Shows the members in the sub club.
     *
     * @param username --> Current username
     * @return --> All sub club dto for frontend
     **/
    @Override
    public List<SubClubDto> getAllSubClubMemberships(String username) {
        Collection<SubClub> subClubs = userRepository.findByUsername(username).getSubclubs();
        log.warn("collection basari ile alindi");
        return Arrays.asList(modelMapper.map(subClubs, SubClubDto[].class));

    }

    /**
     * Shows the members in the sub club by sub club ID.
     *
     * @param subclubId --> Sub club ID
     * @return --> All sub club dto for frontend
     **/
    @Override
    public List<UserDto> getAllSubclubMembers(Long subclubId) {

        Collection<User> members = subClubRepository.getOne(subclubId).getMembers();
        log.warn("members collection basari ile alindi");
        return Arrays.asList(modelMapper.map(members, UserDto[].class));

    }

    /**
     * It is used to assign the sub club admin.
     *
     * @param subclubId --> Sub club ID
     * @param userId    --> User ID
     * @return --> Boolean response
     **/
    @Override
    public Boolean assignAdmin(Long subclubId, Long userId) {
        User user = userRepository.getOne(userId);
        if (user == null)
            return Boolean.FALSE;

        SubClub subclub = subClubRepository.getOne(subclubId);
        if (subclub == null)
            return Boolean.FALSE;

        subclub.setAdmin(user);
        subClubRepository.save(subclub);
        return Boolean.TRUE;
    }

    /**
     * It is used to get the sub club admin.
     *
     * @param subclubId --> Sub club ID
     * @return --> User dto for admin
     **/
    @Override
    public UserDto getSubclubAdmin(Long subclubId) {
        SubClub subclub = subClubRepository.getOne(subclubId);
        if (subclub == null)
            return null;

        if (subclub.getAdmin() == null)
            return null;

        User user = subclub.getAdmin();

        return modelMapper.map(user, UserDto.class);
    }

    /**
     * It is used to create an announcement for the sub club.
     *
     * @param announcementDto --> Announcement dto from frontend.
     * @param subclubId       --> Sub club ID
     * @return --> Boolean response
     **/
    @Override
    public Boolean createNewAnnouncement(AnnouncementDto announcementDto, Long subclubId) {

        SubClub subclub = subClubRepository.getOne(subclubId);
        if (subclub == null)
            return false;

        Announcement ann = new Announcement();
        ann.setSubClub(subclub);
        ann.setContent(announcementDto.getContent());
        ann.setHeadline(announcementDto.getHeadline());
        announcementRepository.save(ann);

        return true;

    }

    /**
     * It is used to delete an announcement.
     *
     * @param annoncementId --> Announcement ID
     * @return --> Boolean response
     **/
    @Override
    public Boolean deleteSubClubAnnouncement(Long annoncementId) {

        Announcement ann = announcementRepository.getOne(annoncementId);

        if (ann != null) {
            announcementRepository.delete(ann);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    /**
     * It is used to get all announcements in the sub club.
     *
     * @param subclubId --> Sub club ID
     * @return --> List of announcements for frontend
     **/
    @Override
    public List<AnnouncementDto> getAllAnnouncements(Long subclubId) {

        Collection<Announcement> annons = announcementRepository.findAllBySubClub_Id(subclubId);
        log.warn("announcement collection basari ile alindi");
        return Arrays.asList(modelMapper.map(annons, AnnouncementDto[].class));
    }

    // FEEDBACK CRUD

    /**
     * Provides feedback for the sub club.
     *
     * @param feedbackDto --> Feedback dto from frontend
     * @param subClubId   --> Sub club ID
     * @param username    --> Current username
     * @return --> Feedback dto for frontend
     **/
    @Override
    public FeedbackDto createNewFeedback(FeedbackDto feedbackDto, Long subClubId, String username) {

        if (!subClubRepository.existsById(subClubId)) {
            log.warn("Error while creating feedback");
            return null;
        }

        SubClub subClub = subClubRepository.getOne(subClubId);

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        Feedback feedback = modelMapper.map(feedbackDto, Feedback.class);
        feedback.setOwnerSubClub(subClub);
        feedback.setOwner(user);
        feedbackRepository.save(feedback);

        subClub.getFeedbacks().add(feedback);
        subClubRepository.save(subClub);

        return modelMapper.map(feedback, FeedbackDto.class);
    }

    /**
     * Get all feedbacks for the sub club.
     *
     * @param subClubId --> Sub club ID
     * @return --> List of feedback dto for frontend
     **/
    @Override
    public List<FeedbackDto> getAllFeedbacks(Long subClubId) {

        List<Feedback> feedbackList = feedbackRepository.getAllByOwnerSubClub_Id(subClubId);
        if (feedbackList == null)
            return null;
        return Arrays.asList(modelMapper.map(feedbackList, FeedbackDto[].class));

    }

    /**
     * Updates the specified feedback for the sub club.
     *
     * @param feedbackDto --> Feedback dto from frontend
     * @param feedbackId  --> Feedback ID
     * @return --> Boolean Response
     **/
    @Override
    public Boolean updateFeedback(FeedbackDto feedbackDto, Long feedbackId) {

        if (!feedbackRepository.existsById(feedbackId))
            return Boolean.FALSE;

        Feedback feedback = feedbackRepository.getOne(feedbackId);
        feedback.setComment(feedbackDto.getComment());
        feedbackRepository.save(feedback);

        return Boolean.TRUE;
    }

    /**
     * Deletes the specified feedback for the sub club.
     *
     * @param feedbackId --> Feedback ID
     * @return --> Boolean Response
     **/
    @Override
    public Boolean deleteFeedback(Long feedbackId) {

        if (!feedbackRepository.existsById(feedbackId))
            return Boolean.FALSE;

        Feedback feedback = feedbackRepository.getOne(feedbackId);
        feedbackRepository.delete(feedback);
        return Boolean.TRUE;

    }

    /**
     * This function retrieves the current authenticated user information.
     *
     * @return --> Current authenticated user
     **/
    private User getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username);
    }
}
