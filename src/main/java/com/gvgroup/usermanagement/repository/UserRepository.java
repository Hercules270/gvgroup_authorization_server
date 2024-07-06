package com.gvgroup.usermanagement.repository;


import com.gvgroup.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameOrEmail(String userName, String email);

    @Query("""
            SELECT DISTINCT u FROM User u
            LEFT JOIN FETCH u.roles r
            WHERE u.userId = :userId
            """)
    Optional<User> findByUserId(@Param("userId") UUID userId);

    void deleteByUserId(UUID userId);
}
