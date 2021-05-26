package com.hacettepe.clubinn.service.implementations;

import com.hacettepe.clubinn.model.dto.*;
import com.hacettepe.clubinn.model.entity.Profile;
import com.hacettepe.clubinn.model.entity.Role;
import com.hacettepe.clubinn.model.entity.User;
import com.hacettepe.clubinn.model.repository.ProfileRepository;
import com.hacettepe.clubinn.model.repository.RoleRepository;
import com.hacettepe.clubinn.model.repository.UserRepository;
import com.hacettepe.clubinn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProfileRepository profileRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }


    @Override
    @Transactional
    public UserDto save(UserDto user) {
        User tempUser = modelMapper.map(user, User.class);
        tempUser = userRepository.save(tempUser);
        user.setId(tempUser.getId());
        System.out.println();
        return user;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Role role = user.getRole();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return authorities;
    }

    @Override
    public UserDto getByUsername(String username) {
        User tempUser = userRepository.findByUsername(username);
        return modelMapper.map(tempUser, UserDto.class);
    }


    public List<UserDto> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDto[].class));
    }

    public void createProfile(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        profileRepository.save(profile);
    }

    @Override
    public ProfileDto getProfile(String username) {
        Profile profile = profileRepository.findByUserUsername(username);
        return modelMapper.map(profile, ProfileDto.class);
    }

    @Override
    public String updateProfile(ProfileDto profileDto, String username) {
        log.warn("updateprofile service func calisiyor:", profileDto.getHobbies());

        Profile currentProfile = profileRepository.findByUserUsername(username);
        log.warn("profile repostory basaili bir sekilde cekildi:", currentProfile.getUser());
        currentProfile.setAbout(profileDto.getAbout());

        currentProfile.setCity(profileDto.getCity());


        currentProfile.setPhone(profileDto.getPhone());
        currentProfile.setHobbies(profileDto.getHobbies());

        profileRepository.save(currentProfile);
        log.warn("save bitti");

        return "Profile is succesfully updated!";

    }

    @Override
    public Boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username.toLowerCase()) != null;
    }


    @Override
    public Boolean isEmailExists(String email) {
        return userRepository.findByEmail(email.toLowerCase()) != null;
    }


    @Override
    public Boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
                log.warn("Register'a giriyor");
                User user = new User();
                user.setEmail(registrationRequest.getEmail().toLowerCase());
                user.setFirstName(registrationRequest.getFirstName());
                user.setSurname(registrationRequest.getSurname());
                user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
                user.setUsername(registrationRequest.getUsername().toLowerCase());
                final Role role = roleRepository.findByName("USER");
                //System.out.println(role.getName());
                user.setRole(role);
                userRepository.save(user);
                this.createProfile(user);
                return Boolean.TRUE;



        } catch (Exception e) {
            log.error("Registration has been failed. Exception=", e);
            return Boolean.FALSE;
        }
    }

    @Override
    public String changePassword(PasswordChangeDto passwordChangeDto, String userName) {

        User currentUser = userRepository.findByUsername(userName);

        if (bCryptPasswordEncoder.matches(passwordChangeDto.getOldPassword(), currentUser.getPassword())) {
            if (passwordChangeDto.getNewPassword().equals(passwordChangeDto.getNewPasswordConfirmation())) {
                String passwordCheck = passwordCheck(passwordChangeDto.getNewPassword());
                if (passwordCheck.equals("true")) {
                    currentUser.setPassword(bCryptPasswordEncoder.encode(passwordChangeDto.getNewPassword()));
                    userRepository.save(currentUser);
                    return "Sifre degistirme islemi basarili !";
                } else {
                    return passwordCheck;
                }
            }
            return "Yeni girilen şifreler birbirleri ile uyuşmuyor !";
        }
        return "Girilen şifre kullanıcının önceki şifresi ile uyuşmuyor";
    }

    private String passwordCheck(String password) {

        if (!((password.length() >= 8) && (password.length() <= 15))) {
            return "Şifreniz 8 ila 15 karakter arasında olmalıdır !";
        }

        if (password.contains(" ")) {
            return "Şifreniz boşluk karakteri içermemelidir ! ";
        }

        int numberCount = 0;

        for (int i = 0; i <= 9; i++) {

            String number = Integer.toString(i);

            if (password.contains(number)) {
                numberCount = 1;
                break;
            }
        }

        if (numberCount == 0) {
            return "Şifreniz bir rakam (0-9) içermelidir !";
        }

        if ((password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            return "Şifreniz özel karakter içermemelidir !";
        }

        int upperCase = 0;
        for (int i = 65; i <= 90; i++) {

            char c = (char) i;
            String upper = Character.toString(c);
            if (password.contains(upper)) {
                upperCase = 1;
                break;
            }
        }
        if (upperCase == 0) {
            return "Şifreniz en az 1 tane büyük karakter içermelidir !";
        }


        int lowerCase = 0;
        for (int i = 90; i <= 122; i++) {

            char c = (char) i;
            String lower = Character.toString(c);

            if (password.contains(lower)) {
                lowerCase = 1;
                break;
            }
        }

        if (lowerCase == 0) {
            return "Şifreniz en az 1 tane küçük karakter içermelidir !";
        }

        return "true";
    }

    @Override
    public String updateProfileWithEmail(UpdateProfileDto updateProfileDto, String username) {

        User currentUser = userRepository.findByUsername(username);
        String email = updateProfileDto.getEmail();
        String check = emailCheck(email);

        if (check.equals("true")) {
            currentUser.setEmail(updateProfileDto.getEmail());
            userRepository.save(currentUser);
            return "Email basariyla degistirildi !";
        }
        return check;
    }

    @Override
    public String emailCheck(String email) {
        if (!email.contains("@")) {
            return "Lütfen geçerli bir email giriniz !";
        }
        return "true";
    }
}

