package com.hb.auth.bootstrap;

import com.hb.auth.exception.NotFoundException;
import com.hb.auth.model.Post;
import com.hb.auth.model.Role;
import com.hb.auth.model.User;
import com.hb.auth.repository.PostRepository;
import com.hb.auth.repository.RoleRepository;
import com.hb.auth.repository.UserRepository;
import com.hb.auth.util.NumberUtils;
import com.hb.auth.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Profile({"dev", "local"})
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
        loadPosts();
    }

    /**
     * Seed Roles in the startup
     */
    public void loadRoles() {
        if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
        Role adminRole = roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        User admin = new User("admin", "admin", 28,"admin_admin_0000", "admin@admin.com", passwordEncoder.encode("password"), roles);

        userRepository.save(admin);
    }
    /**
     * Seed Users in the startup
     */
    public void loadUsers() {

        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new NotFoundException("Role Not Found!"));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User hosni = new User("hosni", "bounechada", 28, "hosni_bounechada_0000", "hosni@gmail.com", passwordEncoder.encode("azerty"), roles);
        User mohammed = new User("mohammed", "bounab", 26, "mohammed_bounab_0000", "mohammed@gmail.com", passwordEncoder.encode("azerty"), roles);

        List<Post> posts = List.of(
                new Post(2L, "First Hosni Post", hosni),
                new Post(3L, "First Mohammed Post", mohammed)
        );
        postRepository.saveAll(posts);
    }
    /**
     * Seed Posts in the startup
     */
    public void loadPosts() {
        User hosni = userRepository.findById(2L).orElseThrow(() -> new NotFoundException("User Not Found!"));
        Post post = new Post(3L, "Second Hosni Post", hosni);
        postRepository.save(post);
    }
}
