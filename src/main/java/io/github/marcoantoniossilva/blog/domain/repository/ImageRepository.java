package io.github.marcoantoniossilva.blog.domain.repository;

import io.github.marcoantoniossilva.blog.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.net.URI;
import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

  List<Image> findAllByPostId(Long postId);

  URI getUriByPostId(Long postId);
}
