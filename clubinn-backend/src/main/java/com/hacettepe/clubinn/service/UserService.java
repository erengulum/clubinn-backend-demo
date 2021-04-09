package com.hacettepe.clubinn.service;

import com.hacettepe.clubinn.model.dto.ProfileDto;
import com.hacettepe.clubinn.model.dto.RegistrationRequest;
import com.hacettepe.clubinn.model.dto.UserDto;
import com.hacettepe.clubinn.model.entity.User;

import java.util.List;

public interface UserService {


    UserDto getByUsername(String username);

    UserDto save(UserDto user);

    List<UserDto> getAll();

    Boolean isUsernameExists(String username);

    Boolean isEmailExists(String email);

    Boolean deleteUser(Long id);

    Boolean register(RegistrationRequest registrationRequest);

    String updateProfile(ProfileDto profile,String username);
    ProfileDto getProfile(String username);
    void createProfile(User user);


}