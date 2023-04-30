package com.hb.auth.repository;

import com.hb.auth.model.Post;
import com.hb.auth.view.PostView;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from Post p where p.id = :id")
    Integer deletePostById(@Param("id") Long id);

    Page<PostView> findByUserId(Long userId, Pageable pageable);
}
