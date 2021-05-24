package com.hacettepe.clubinn.controller;

import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.dto.AnnouncementDto;
import com.hacettepe.clubinn.model.dto.JoinDto;
import com.hacettepe.clubinn.model.dto.SubClubDto;
import com.hacettepe.clubinn.model.dto.UserDto;
import com.hacettepe.clubinn.service.SubClubService;
import com.hacettepe.clubinn.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/subclubs")
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@Api(value = "Sub Club API")
public class SubClubController {

    private final SubClubService subClubService;
    private final ResponseMessage responseMessage;

    public SubClubController(SubClubService subClubService, ResponseMessage responseMessage) {
        this.subClubService = subClubService;
        this.responseMessage = responseMessage;
    }

    @RequestMapping(value = "join", method = RequestMethod.POST)
    @ApiOperation(value = "Join Sub Club Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> joinSubClub(@Validated @RequestBody JoinDto joinDto) {

        Boolean response = subClubService.join(joinDto);

        if (response) {
            responseMessage.setResponseMessage("Basarili bir sekilde kaydoldunuz");
            return ResponseEntity.ok(responseMessage);

        } else {
            responseMessage.setResponseMessage("Bir hata meydana geldi. Lütfen tekrar deneyin");
            return ResponseEntity.badRequest().body(responseMessage);
        }
    }

    @RequestMapping(value = "getall/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "Get SubClub Memberships Operation", response = SubClubDto.class)
    public ResponseEntity<List<SubClubDto>> getSubclubMemberships(@PathVariable String username) {

        List<SubClubDto> subClubDtos = subClubService.getAllSubClubMemberships(username);

        return ResponseEntity.ok(subClubDtos);
    }

    @RequestMapping(value = "getmembers/{subclubId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get SubClub Members Operation", response = UserDto.class)
    public ResponseEntity<List<UserDto>> getMembersOfSubclub(@PathVariable Long subclubId) {

        List<UserDto> members = subClubService.getAllSubclubMembers(subclubId);

        return ResponseEntity.ok(members);
    }

    @RequestMapping(value = "/{subclubid}", method = RequestMethod.GET)
    @ApiOperation(value = "Get SubClub Operation", response = SubClubDto.class)
    public ResponseEntity<SubClubDto> getSubClub(@PathVariable Long subclubid) {
        return ResponseEntity.ok(subClubService.getOne(subclubid));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "Get All SubClub Operation", response = SubClubDto.class)
    public ResponseEntity<List<SubClubDto>> getAllSubClubDto() {
        List<SubClubDto> data = subClubService.getAll();
        return ResponseEntity.ok(data);
    }

    @RequestMapping(value = "/all/{categoryId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get All SubClub by Category Operation", response = SubClubDto.class)
    public ResponseEntity<List<SubClubDto>> getAllSubClubsByCategory(@PathVariable Long categoryId) {
        List<SubClubDto> data = subClubService.getAllByCategory(categoryId);
        return ResponseEntity.ok(data);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Create SubClub Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> createNewClub(@Validated @RequestBody SubClubDto subClubDto) {

        Boolean response = subClubService.createNewSubClub(subClubDto);

        if (!response) {
            responseMessage.setResponseMessage("Yeni subclub  oluşturulurken bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Yeni subclub başarıyla oluşturuldu!");
            return ResponseEntity.ok(responseMessage);
        }

    }

    @RequestMapping(value = "/delete/{subClubId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete SubClub Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> deleteClubCategory(@PathVariable Long subClubId) {

        Boolean response = subClubService.deleteSubClub(subClubId);

        if (!response) {
            responseMessage.setResponseMessage("Silme işlemi sirasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Silme islemi basariyla gerceklestirildi");
            return ResponseEntity.ok(responseMessage);
        }

    }

    //ANNOUNCEMENTS

    @RequestMapping(value = "announcements/all/{subclubId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get All Announcement Operation", response = AnnouncementDto.class)
    public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(@PathVariable Long subclubId) {
        List<AnnouncementDto> data = subClubService.getAllAnnouncements(subclubId);
        return ResponseEntity.ok(data);
    }

    @RequestMapping(value = "announcements/create/{subclubId}", method = RequestMethod.POST)
    @ApiOperation(value = "Create Announcement Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> createNewAnnouncement(@PathVariable Long subclubId, @Validated @RequestBody AnnouncementDto announcementDto) {

        Boolean response = subClubService.createNewAnnouncement(announcementDto, subclubId);

        if (!response) {
            responseMessage.setResponseMessage("Yeni duyuru oluşturulurken bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Yeni duyuru başarıyla oluşturuldu!");
            return ResponseEntity.ok(responseMessage);
        }

    }

    @RequestMapping(value = "announcements/delete/{announcementId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete Announcement Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> deletAnnons(@PathVariable Long announcementId) {

        Boolean response = subClubService.deleteSubClubAnnouncement(announcementId);

        if (!response) {
            responseMessage.setResponseMessage("Silme işlemi sirasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        } else {
            responseMessage.setResponseMessage("Silme islemi basariyla gerceklestirildi");
            return ResponseEntity.ok(responseMessage);
        }

    }

    //Administration
    @RequestMapping(value = "/{subclubId}/admin/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "Assign Admin Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> assignAdmin(@PathVariable("subclubId") Long subclubId, @PathVariable("userId") Long userId) {
        Boolean response = subClubService.assignAdmin(subclubId, userId);

        if (response) {
            responseMessage.setResponseMessage("Admin atama işlemi basariyla tamamlanmıştır");
            return ResponseEntity.ok(responseMessage);
        } else {
            responseMessage.setResponseMessage("Admin atama işlemi sırasında bir hata meydana geldi");
            return ResponseEntity.badRequest().body(responseMessage);
        }

    }

    //Get Admin of the subclub
    @RequestMapping(value = "/getadmin/{subclubId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get Admin Operation", response = UserDto.class)
    public ResponseEntity<UserDto> getAdmin(@PathVariable Long subclubId) {
        UserDto response = subClubService.getSubclubAdmin(subclubId);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }

    }

}
