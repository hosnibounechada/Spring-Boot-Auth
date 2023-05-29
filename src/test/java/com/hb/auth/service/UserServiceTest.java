package com.hb.auth.service;

import com.hb.auth.common.service.MinioService;
import com.hb.auth.mapper.UserMapper;
import com.hb.auth.model.postgres.User;
import com.hb.auth.payload.request.user.CreateUserRequest;
import com.hb.auth.repository.PostRepository;
import com.hb.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;
    @Mock
    UserMapper userMapper;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    MinioService minioService;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    private UserService underTest;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository, postRepository, userMapper, passwordEncoder,minioService);
    }
    @Test
    void itShouldCreateNewUser() {
        // Given
        Long id = 1L;
        String firstName = "john";
        String lastName = "doe";
        int age = 28;
        String username = "john_doe_0000";
        String email = "john@doe.com";
        String password = "Azer123&";

        User user = User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .username(username)
                .email(email)
                .password(password)
                .build();

        // ... Request
        CreateUserRequest request = new CreateUserRequest(firstName, lastName, age, email,password);
        // ... No User with username or email
        given(userRepository.findByUsernameOrEmail(username, email)).willReturn(Optional.empty());

        given(userMapper.requestToEntity(request)).willReturn(user);
        // When
        underTest.create(request);
        // Then
        then(userRepository).should().save(userArgumentCaptor.capture());

        User userArgumentCaptorValue = userArgumentCaptor.getValue();

        assertThat(userArgumentCaptorValue).isEqualTo(user);
    }

    @Test
    void itShouldUpdateUser() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldDeleteUser() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldGetUser() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldGetAllUsers() {
        // Given
        // When
        // Then
    }
}