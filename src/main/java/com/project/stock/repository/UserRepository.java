package com.project.stock.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.project.stock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}