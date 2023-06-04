package com.hb.auth.service;

import com.hb.auth.common.service.MailService;
import com.hb.auth.common.service.RedisService;
import com.hb.auth.common.service.TwilioService;
import com.hb.auth.common.template.MailTemplate;
import com.hb.auth.exception.InvalidCredentialsException;
import com.hb.auth.exception.NotFoundException;
import com.hb.auth.exception.ResourceAlreadyExistsException;
import com.hb.auth.mapper.UserMapper;
import com.hb.auth.model.postgres.Role;
import com.hb.auth.model.postgres.User;
import com.hb.auth.payload.response.auth.EmailAvailabilityResponse;
import com.hb.auth.payload.response.auth.LoginResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.repository.RoleRepository;
import com.hb.auth.repository.UserRepository;
import com.hb.auth.security.jwt.JwtUtils;
import com.hb.auth.security.service.TokenService;
import com.hb.auth.util.NumberUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hb.auth.constant.RedisEmailConfirmationHash.*;
import static com.hb.auth.util.JwtUtil.assignJwtToCookie;
import static com.hb.auth.util.NumberUtils.*;
import static com.hb.auth.util.StringUtils.generateUsername;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final MailService mailService;
    private final RedisService redisService;
    private final TwilioService twilioService;

    private final JwtUtils jwtUtils;

    public UserResponse registerUser(String firstName, String lastName, int age, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new NotFoundException("Role USER doesn't exist"));

        String username = generateUsername(firstName, lastName, NumberUtils::Generate4DigitsNumber);

        boolean exist = userRepository.findByUsernameOrEmail(username, email).isPresent();

        if (exist) throw new ResourceAlreadyExistsException("User already exist");

        Set<Role> authorities = Stream.of(userRole).collect(Collectors.toSet());

        User savedUser = userRepository.save(new User(firstName, lastName, age, username, email, encodedPassword, authorities));

        String confirmationCode = Generate6DigitsNumber().toString();

        Map<String, String> map = Map.of(CODE, confirmationCode, COUNTER, "0");

        redisService.createHashWithTtl(email, map, 350L);

        mailService.sendMailAsync(MailTemplate.accountConfirmationMail(email, confirmationCode));

        return userMapper.entityToResponse(savedUser);
    }

    public LoginResponse loginUser(String username, String password, HttpServletResponse response) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = (User) auth.getPrincipal();
            // Asymmetric RSA
            // String jwt = tokenService.generateJwt(auth);
            // Symmetric
            String accessToken = jwtUtils.generateJwtToken(auth, true);

            UserResponse userResponse = userMapper.entityToResponse(user);

            String refreshToken = jwtUtils.generateJwtToken(auth, false);

            assignJwtToCookie(refreshToken, response);

            return new LoginResponse(userResponse, accessToken);

        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid Credentials, Username or Password is wrong");
        }
    }

    public Boolean confirmEmail(String email, String code) {

        Map<String, String> map = redisService.getFieldValuesAndIncrement(email);

        if (map == null) throw new NotFoundException("Invalid code or Already expired");

        if (!map.get(CODE).equals(code)) throw new NotFoundException("Incorrect code");

        // validate in the database*/

        return true;
    }

    public Boolean sendOTP(String phone) {
        return twilioService.sendOTP(phone);
    }

    public Boolean verifyOTP(Long id, String phone, String otp) {
        boolean result = twilioService.verifyOTP(phone, otp);

        if (!result) throw new NotFoundException("Operation doesn't Complete");

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        user.setPhone(phone);

        userRepository.save(user);

        return true;
    }

    public EmailAvailabilityResponse emailAvailability(String email){
        boolean exist = userRepository.existsByEmail(email);

        if(exist) throw new ResourceAlreadyExistsException("Email Already exists");

        return new EmailAvailabilityResponse(true);
    }

    public LoginResponse me(){
        return null;
    }
}