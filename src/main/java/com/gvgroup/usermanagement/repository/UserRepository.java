package com.gvgroup.usermanagement.repository;


import com.gvgroup.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findUserByUserNameOrEmail(String userName, String email);
}
