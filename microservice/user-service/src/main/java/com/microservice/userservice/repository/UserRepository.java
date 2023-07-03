package com.microservice.userservice.repository;

import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.username, u.email, u.password FROM User u")
    List<UserDto> findAllParserDto();

    @Query("SELECT u.username, u.email, u.password FROM User u WHERE u.id = ?1")
    Optional<UserDto> findByIdParserDto(Long id);

    Optional<User> findByEmail(String email);
}
