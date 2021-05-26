package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.*;
import com.hacettepe.clubinn.model.entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    UserDto getByUsername(String username);

    UserDto save(UserDto user);

    List<UserDto> getAll();

    Boolean isUsernameExists(String username);

    Boolean isEmailExists(String email);

    Boolean deleteUser(Long id);

    Boolean register(RegistrationRequest registrationRequest);

    String updateProfile(ProfileDto profile, String username);

    ProfileDto getProfile(String username);

    void createProfile(User user);

    String changePassword(PasswordChangeDto passwordChangeDto, String username);

    String updateProfileWithEmail(UpdateProfileDto updateProfileDto, String username);

    void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException;
    String emailCheck(String email);

}