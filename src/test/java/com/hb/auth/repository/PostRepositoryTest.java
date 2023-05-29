package com.hb.auth.repository;

import com.hb.auth.model.postgres.Post;
import com.hb.auth.model.postgres.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
class PostRepositoryTest {
    @Autowired
    private PostRepository underTest;

    @Test
    void itShouldDeletePostById() {
        // Given
        // When
        // Then
    }

    @Test
    void itShouldFindByUserId() {
        // Given
        // When
        // Then
    }
    @Test
    void itShouldSavePost(){
        // Given
        Long userId = 1L;
        String firstName = "john";
        String lastName = "doe";
        int age = 28;
        String username = "john_doe_0000";
        String email = "john@doe.com";
        String password = "Azer123&";

        User user = User.builder()
                .id(userId)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .username(username)
                .email(email)
                .password(password)
                .build();

        Long postId = 1L;
        String content = "Post content!";
        Post post = Post.builder()
                .id(postId)
                .user(user)
                .content(content)
                .build();
        // When
        underTest.save(post);
        // Then
        Optional<Post> optionalPost = underTest.findById(postId);
        assertThat(optionalPost).hasValueSatisfying(p -> {
            assertThat(p.getId()).isEqualTo(postId);
            assertThat(p.getContent()).isEqualTo(content);
            assertThat(p.getUser()).isEqualTo(user);
        });
    }
}