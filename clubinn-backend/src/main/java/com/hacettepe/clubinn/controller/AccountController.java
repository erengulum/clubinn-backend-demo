package com.hacettepe.clubinn.controller;
import com.hacettepe.clubinn.model.dto.LoginRequest;
import com.hacettepe.clubinn.model.dto.RegistrationRequest;
import com.hacettepe.clubinn.model.dto.TokenResponse;
import com.hacettepe.clubinn.model.entity.User;
import com.hacettepe.clubinn.config.helper.RegistrationHelper;
import com.hacettepe.clubinn.config.helper.ResponseMessage;
import com.hacettepe.clubinn.model.repository.UserRepository;
import com.hacettepe.clubinn.config.security.JwtTokenUtil;
import com.hacettepe.clubinn.service.implementations.UserServiceImpl;
import com.hacettepe.clubinn.util.ApiPaths;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Slf4j
@RestController
@RequestMapping("/api/token") //Token pathine gelen bütün isteklere izin verilecek.Bunun ayarı SecurityConfig'de
@CrossOrigin(origins = ApiPaths.LOCAL_CLIENT_BASE_PATH, maxAge = 3600)
public class AccountController {

    @Autowired
    ResponseMessage responseMessage;

    @Autowired
    RegistrationHelper registrationHelper;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public AccountController(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil,UserRepository userRepository,UserServiceImpl userService){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;

    }

    @RequestMapping(method = RequestMethod.POST) //LoginRequest'i DTO kısmında olusturduk.Front-end'den gelen login objesi gibi düsün
    @ResponseBody
    public ResponseEntity<TokenResponse> login(@Validated @RequestBody LoginRequest loginRequest, HttpServletResponse response, HttpServletRequest request) throws AuthenticationException {
        log.warn("login'e girdi");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername().toLowerCase(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        // Add a session cookie
        Cookie sessionCookie = new Cookie( "someSessionId", token );
        // httpsler icin bu degisecek deploy sonrasi
        sessionCookie.setSecure(request.isSecure());
        response.addCookie( sessionCookie );


        User userFromDB = userRepository.findByUsername(loginRequest.getUsername().toLowerCase());


        //Burada sorun olabilir:Bunu yaz:         return ResponseEntity.ok(new AuthToken(token));
        return ResponseEntity.ok(new TokenResponse(userFromDB.getUsername(),userFromDB.getFirstName(), userFromDB.getSurname(), userFromDB.getRole().getName(),token));
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value="Register Operation",response = Boolean.class)
    public ResponseEntity<ResponseMessage> register(@Validated @RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {

        responseMessage.setResponseMessage(registrationHelper.registrationValidator(registrationRequest));

        if(responseMessage.getResponseMessage()!=null){
            responseMessage.setResponseType(0);
            return ResponseEntity.ok(responseMessage);
        }

        Boolean response = userService.register(registrationRequest);

        if(!response){
            responseMessage.setResponseMessage("Kayıt sırasında bir hata meydana geldi.Lütfen tekrar deneyiniz");
            return ResponseEntity.badRequest().body(responseMessage);
        }

        responseMessage.setResponseType(1);
        responseMessage.setResponseMessage("Basariyla kaydoldunuz!");
        return ResponseEntity.ok(responseMessage);

    }

}
