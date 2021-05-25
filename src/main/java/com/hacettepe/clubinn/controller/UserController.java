package com.hacettepe.clubinn.controller;

import com.hacettepe.clubinn.model.dto.PasswordChangeDto;
import com.hacettepe.clubinn.model.dto.ProfileDto;
import com.hacettepe.clubinn.model.dto.UpdateProfileDto;
import com.hacettepe.clubinn.model.dto.UserDto;
import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.service.UserService;
import com.hacettepe.clubinn.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
@RequestMapping(ApiPaths.UserPath.CTRL)
@RestController
@Api(value = "User API")
public class UserController {

    private final UserService userService;
    private final ResponseMessage responseMessage;

    public UserController(UserService userService, ResponseMessage responseMessage) {
        this.userService = userService;
        this.responseMessage = responseMessage;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "Get One User Operation", response = UserDto.class)
    public ResponseEntity<UserDto> getOne(@PathVariable String username) {
        log.warn("GetOne(User icin) metodu basariyla calisti");
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "Get All Users Operation", response = UserDto.class)
    public ResponseEntity<List<UserDto>> listUsers() {
        log.warn("listUsers(Admin icin) metodu basariyla calisti");
        List<UserDto> data = userService.getAll();
        return ResponseEntity.ok(data);
    }

/*
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updateUser(@RequestBody UserDto userDto){
        log.warn("update controllerına girdi");
        responseMessage.setResponseMessage(userService.updateUser(userDto));
        return ResponseEntity.ok(responseMessage);
    }

*/

    @RequestMapping(value = "/profile/update/{username}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Profile Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> updateProfile(@RequestBody ProfileDto profileDto, @PathVariable String username) {
        log.warn("update profile controllerına girdi");
        responseMessage.setResponseMessage(userService.updateProfile(profileDto, username));
        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "Get Profile Operation", response = ProfileDto.class)
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String username) {
        log.warn("get profile(User icin) metodu basariyla calisti");
        return ResponseEntity.ok(userService.getProfile(username));
    }

    @RequestMapping(value = "/profile/changepassword/{username}", method = RequestMethod.PUT)
    @ApiOperation(value = "Change Password Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> changePassword(@RequestBody PasswordChangeDto passwordChangeDto, @PathVariable String username) {
        responseMessage.setResponseMessage(userService.changePassword(passwordChangeDto, username));
        return ResponseEntity.ok(responseMessage);
    }

    @RequestMapping(value = "/profile/email/{username}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Email Operation", response = ResponseMessage.class)
    public ResponseEntity<ResponseMessage> updateEmail(@RequestBody UpdateProfileDto updateProfileDto, @PathVariable String username) {
        responseMessage.setResponseMessage(userService.updateProfileWithEmail(updateProfileDto, username));
        return ResponseEntity.ok(responseMessage);
    }

}
