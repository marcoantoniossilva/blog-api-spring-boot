package io.github.marcoantoniossilva.blog.domain.repository;

import io.github.marcoantoniossilva.blog.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

}
