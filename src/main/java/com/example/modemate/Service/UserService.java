package com.example.modemate.Service;

import com.example.modemate.DTO.UserLoginDTO;
import com.example.modemate.DTO.UserRegisterDTO;
import com.example.modemate.Repository.UserRepository;
import com.example.modemate.Security.custom.CustomUserInfoDto;
import com.example.modemate.Security.jwt.JwtUtil;
import com.example.modemate.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Transactional
    public void register(UserRegisterDTO userDTO) {
        log.info("[User Service] register");

        User user = new User(userDTO.getEmail(),userDTO.getNickname(), encoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }
    public String login(UserLoginDTO userLoginDTO) {
        log.info("[User Service] login");

        User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow();

        if(user == null) {
            return "user not found";
        }

        if(!encoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return "password error";
        }

        CustomUserInfoDto customUserInfoDto = new CustomUserInfoDto(user.getId(), user.getEmail(), user.getPassword());
        return jwtUtil.createAccessToken(customUserInfoDto);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

}
