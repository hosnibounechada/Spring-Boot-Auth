package com.hb.auth.bootstrap;

import com.hb.auth.exception.NotFoundException;
import com.hb.auth.model.Post;
import com.hb.auth.model.User;
import com.hb.auth.repository.PostRepository;
import com.hb.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile({"dev", "local"})
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public DataLoader(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
        loadPosts();
    }

    public void loadUsers() {

        User hosni = new User("hosni", "bounechada", 28, "hosni@gmail.com", "azerty");
        User mohammed = new User("mohammed", "bounab", 26, "mohammed@gmail.com", "azerty");

        List<Post> posts = List.of(
                new Post(1L, "First Hosni Post", hosni),
                new Post(2L, "First Mohammed Post", mohammed)
        );
        postRepository.saveAll(posts);
    }

    public void loadPosts() {
        User hosni = userRepository.findById(1L).orElseThrow(() -> new NotFoundException("User Not Found!"));
        Post post = new Post(3L, "Second Hosni Post", hosni);
        postRepository.save(post);
    }
}
