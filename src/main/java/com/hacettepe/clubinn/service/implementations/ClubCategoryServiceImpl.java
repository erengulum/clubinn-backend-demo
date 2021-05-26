package com.hacettepe.clubinn.service.implementations;

import com.hacettepe.clubinn.model.dto.ClubCategoryDto;
import com.hacettepe.clubinn.model.dto.ClubCategoryRequestDto;
import com.hacettepe.clubinn.model.entity.ClubCategory;
import com.hacettepe.clubinn.model.entity.ClubCategoryRequest;
import com.hacettepe.clubinn.model.repository.ClubCategoryRepository;
import com.hacettepe.clubinn.model.repository.ClubCategoryRequestRepository;
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

    @Autowired
    private ClubCategoryRequestRepository clubCategoryRequestRepository;

    /**
     * It takes the club category with the id coming from the parameter.
     *
     * @param id --> Club category ID
     * @return --> ClubCategoryDto
     **/
    @Override
    public ClubCategoryDto getOne(Long id) {
        ClubCategory category = clubCategoryRepository.getOne(id);
        if (category == null) {
            return null;
        } else {
            return modelMapper.map(category, ClubCategoryDto.class);
        }
    }

    /**
     * Retrieves all club categories.
     *
     * @return --> List of ClubCategoryDto
     **/
    @Override
    public List<ClubCategoryDto> getAll() {
        List<ClubCategory> clubcategories = clubCategoryRepository.findAll();
        if (clubcategories == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(clubcategories, ClubCategoryDto[].class));
        }
    }

    /**
     * Deletes the club category with the ID coming as parameter.
     *
     * @param clubId --> Club category ID
     * @return --> List of ClubCategoryDto
     **/
    @Override
    public Boolean delete(Long clubId) {
        log.warn("club category-delete servise geldi");
        try {

            ClubCategory clubcategory = clubCategoryRepository.getOne(clubId);
            if (clubcategory != null) {
                clubCategoryRepository.delete(clubcategory);
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            log.error(" Silme sirasında bir sorun meydana geldi. Exception=", e);
            return Boolean.FALSE;
        }

    }

    /**
     * Creates a new club category.
     *
     * @param clubCategoryDto --> Club category dto from frontend
     * @return --> Response message
     **/
    @Override
    public String createNewClub(ClubCategoryDto clubCategoryDto) {
        Boolean response = clubCategoryRepository.existsByClubCategoryName(clubCategoryDto.getClubCategoryName());
        if (response) {
            return "There is already a existing club category with same name";
        }
        ClubCategory clubCategory = modelMapper.map(clubCategoryDto, ClubCategory.class);
        clubCategoryRepository.save(clubCategory);
        return "New Club Category is sucessfully created";
    }

    /**
     * Updates club category.
     *
     * @param clubCategoryDto --> Club category dto from frontend
     * @return --> Club Category Dto for frontend
     **/
    @Override
    public ClubCategoryDto updateSubClub(ClubCategoryDto clubCategoryDto) {
        return null;
    }

    /**
     * Creates a request for the new club category.
     *
     * @param ccr --> Club category request dto from frontend
     * @return --> Response message
     **/
    @Override
    public String requestClubCategory(ClubCategoryRequestDto ccr) {
        try {
            Boolean response = clubCategoryRepository.existsByClubCategoryName(ccr.getClubCategoryName());
            Boolean requestResponse = clubCategoryRequestRepository.existsByClubCategoryName(ccr.getClubCategoryName());
            if (response) {
                return "Failed: There is already a existing club that has the same name";
            }

            if (requestResponse) {
                return "Failed: There is already a existing club request that has the same name";
            }

            ClubCategoryRequest clubCategoryRequest = modelMapper.map(ccr, ClubCategoryRequest.class);
            clubCategoryRequestRepository.save(clubCategoryRequest);
            return "Your request is successfully added to list!";
        } catch (Exception e) {
            return "There is an error occured while trying to process your club request. Please try again";
        }

    }

    /**
     * Allows to vote for club category request.
     *
     * @param requestId --> Request ID
     * @return --> Boolean
     **/
    @Override
    public Boolean voteForRequest(Long requestId) {
        log.warn("servise girdi id:", requestId);
        ClubCategoryRequest clubCategoryRequest = clubCategoryRequestRepository.getOne(requestId);

        log.warn("request bulundu:", clubCategoryRequest.getClubCategoryName());

        int totalVotes = clubCategoryRequest.getNumberOfVotes();
        totalVotes++;

        clubCategoryRequest.setNumberOfVotes(totalVotes);

        clubCategoryRequestRepository.save(clubCategoryRequest);

        log.warn("oylama basarili");
        return true;
    }

    /**
     * Receives all club category requests.
     *
     * @return --> List of club category request dto
     **/
    @Override
    public List<ClubCategoryRequestDto> getAllRequests() {
        List<ClubCategoryRequest> clubCatRequests = clubCategoryRequestRepository.findAll();
        if (clubCatRequests == null) {
            return null;
        } else {
            return Arrays.asList(modelMapper.map(clubCatRequests, ClubCategoryRequestDto[].class));
        }
    }

    /**
     * Deletes the club category request.
     *
     * @param requestId --> Club category request ID
     * @return --> Boolean
     **/
    @Override
    public Boolean deleteClubRequest(Long requestId) {
        log.warn("delete- request id", requestId);
        try {

            Boolean isExists = clubCategoryRequestRepository.existsById(requestId);
            if (isExists) {
                clubCategoryRequestRepository.deleteById(requestId);
                log.warn("req silme basarili");
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            log.error(" Request deletion sirasında bir sorun meydana geldi. Exception=", e);
            return Boolean.FALSE;
        }
    }

    /**
     * It evaluates the club category request and converts it into a club category.
     *
     * @param clubCategoryRequestDto --> Club category request dto
     * @return --> Response message
     **/
    @Override
    public String convertRequestToClub(ClubCategoryRequestDto clubCategoryRequestDto) {
        try {

            ClubCategory clubCategory = modelMapper.map(clubCategoryRequestDto, ClubCategory.class);
            clubCategoryRepository.save(clubCategory);
            log.warn("Kayit islemi basarili");
            clubCategoryRequestRepository.deleteById(clubCategoryRequestDto.getReq_id());
            return "Club is succesfully created and request is deleted from the list";
        } catch (Exception e) {
            return "An error is occured! Please try again!";
        }


    }

}
