package com.hb.auth.service;

import com.hb.auth.exception.InvalidCredentialsException;
import com.hb.auth.exception.ResourceAlreadyExistsException;
import com.hb.auth.mapper.UserMapper;
import com.hb.auth.model.Role;
import com.hb.auth.model.User;
import com.hb.auth.payload.response.auth.LoginResponse;
import com.hb.auth.payload.response.user.UserResponse;
import com.hb.auth.repository.RoleRepository;
import com.hb.auth.repository.UserRepository;
import com.hb.auth.security.service.TokenService;
import com.hb.auth.view.UserViewImp;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.hb.auth.util.JwtUtil.assignJwtToCookie;
import static com.hb.auth.util.StringUtils.*;


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

    public UserResponse registerUser(String firstName, String lastName, int age, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        Optional<Role> userRole = roleRepository.findByAuthority("USER");

        if (userRole.isEmpty()) throw new IllegalStateException("Role USER doesn't exist");

        String username = generateUsername(firstName, lastName);

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) throw new ResourceAlreadyExistsException("User already exist");

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole.get());

        User savedUser = userRepository.save(new User(firstName, lastName, age, username, email, encodedPassword, authorities));

        return userMapper.entityToResponse(savedUser);
    }

    public LoginResponse loginUser(String username, String password, HttpServletResponse response) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String jwt = tokenService.generateJwt(auth);

            User user = (User) auth.getPrincipal();
            UserViewImp userViewImp = new UserViewImp(user.getId(), user.getFirstName(), user.getLastName(), user.getAge(), user.getUsername(), user.getEmail());

            assignJwtToCookie(jwt,response);

            return new LoginResponse(userViewImp, jwt);

        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid Credentials, Username or Password is wrong");
        }
    }

}