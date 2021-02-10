package com.priscilasanfer.concrete.repository;

import com.priscilasanfer.concrete.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
