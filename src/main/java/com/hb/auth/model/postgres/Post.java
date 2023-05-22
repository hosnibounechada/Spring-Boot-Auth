package com.hb.auth.model.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Post")
@Table(
        name = "posts"
)
@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "posts_user_id_fkey"))
    private User user;

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
    }
}
