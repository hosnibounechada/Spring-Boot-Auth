package com.hb.auth.repository;

import com.hb.auth.model.postgres.User;
import com.hb.auth.view.UserView;
import com.hb.auth.view.UserViewImp;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    @Modifying
    @Transactional
    @Query(value = "delete from User u where u.id = :id")
    Integer deleteUserById(@Param("id") Long id);

    List<UserView> getUserByFirstNameOrLastNameOrderByFirstNameAscLastNameAsc(String firstName, String lastName);
    Page<UserViewImp> getUserByFirstNameOrLastName(String firstName, String lastName, Pageable pageable);
}
