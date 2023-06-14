package com.hb.auth.model.postgres;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "Post")
@Table(
        name = "posts"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "posts_user_id_fkey"))
    @ToString.Exclude
    private User user;

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return getId() != null && Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
