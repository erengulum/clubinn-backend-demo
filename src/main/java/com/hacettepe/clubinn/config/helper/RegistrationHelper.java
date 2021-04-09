package com.hacettepe.clubinn.config.helper;

import com.hacettepe.clubinn.model.dto.RegistrationRequest;
import com.hacettepe.clubinn.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class RegistrationHelper {
    private final UserService userService;

    public RegistrationHelper(UserService userService) {
        this.userService = userService;
    }

    public String registrationValidator(RegistrationRequest registrationRequest){

        if(userService.isUsernameExists(registrationRequest.getUsername())){
            return "Kullanıcı adı/Kimlik Numarası zaten kullanılmakta.";
        }

        if(userService.isEmailExists(registrationRequest.getEmail())){
            return "Email zaten kullanılmakta.";
        }

        return null;


    }




}
