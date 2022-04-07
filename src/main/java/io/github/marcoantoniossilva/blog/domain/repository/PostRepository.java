package io.github.marcoantoniossilva.blog.domain.repository;

import io.github.marcoantoniossilva.blog.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
