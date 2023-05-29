package com.hb.auth.repository;

import com.hb.auth.model.postgres.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;
    @Test
    void itShouldFindUserByUsernameOrEmail() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldSaveUser() {
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

        // When
        underTest.save(user);

        // Then
        Optional<User> optionalUser = underTest.findById(id);
        assertThat(optionalUser)
                .isPresent()
                .hasValueSatisfying(u -> {
                    assertThat(u.getId()).isEqualTo(id);
                    assertThat(u.getFirstName()).isEqualTo(firstName);
                    assertThat(u.getLastName()).isEqualTo(lastName);
                    assertThat(u.getAge()).isEqualTo(age);
                    assertThat(u.getUsername()).isEqualTo(username);
                    assertThat(u.getEmail()).isEqualTo(email);
                    assertThat(u.getPassword()).isEqualTo(password);
                });
    }
}