package io.github.marcoantoniossilva.blog.domain.repository;

import io.github.marcoantoniossilva.blog.domain.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
