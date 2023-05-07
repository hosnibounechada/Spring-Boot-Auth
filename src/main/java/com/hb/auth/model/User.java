package com.hb.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Entity(name = "User")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "user_phone_unique", columnNames = "phone")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            length = 50
    )
    // Should be implemented conditionally based on the database dialect
    // initcap function uppercase the first letter of each word in the database layer
    // @ConditionalOnPostgres
    // @ColumnTransformer(read = "initcap(first_name)")
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 50
    )
    private String lastName;

    @Column(
            name = "age",
            nullable = false

    )
    private Integer age;

    @Column(
            name = "username",
            nullable = false,
            length = 128
    )
    private String username;
    @Column(
            name = "email",
            nullable = false,
            length = 128
    )
    private String email;
    @Column(
            name = "phone",
            nullable = true,
            length = 15
    )
    private String phone;

    @Column(
            name = "password",
            nullable = false,
            length = 128
    )
    private String password;

    @Column(
            name = "profile_picture",
            columnDefinition = "TEXT"
    )
    private String profilePicture;

    @Column(
            name = "profile_thumbnail",
            columnDefinition = "TEXT"
    )
    private String profileThumbnail;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Post> posts;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;


    public User(String firstName, String lastName, Integer age, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String firstName, String lastName, Integer age, String username, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String firstName, String lastName, Integer age, String username, String email, String password, Set<Role> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
