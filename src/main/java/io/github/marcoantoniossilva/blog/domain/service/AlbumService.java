package io.github.marcoantoniossilva.blog.domain.service;

import io.github.marcoantoniossilva.blog.security.SecurityUtils;
import io.github.marcoantoniossilva.blog.domain.exception.ResourceNotFound;
import io.github.marcoantoniossilva.blog.domain.exception.UnauthorizedException;
import io.github.marcoantoniossilva.blog.domain.model.Album;
import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

  private final AlbumRepository albumRepository;

  public AlbumService(AlbumRepository albumRepository) {
    this.albumRepository = albumRepository;
  }

  public List<Album> listAll() {
    return albumRepository.findAll();
  }

  public Optional<Album> findById(Long albumId) {
    return albumRepository.findById(albumId);
  }

  @Transactional
  public Album save(Album album) {
    album.setUser(SecurityUtils.getLoggedUser());
    return albumRepository.save(album);
  }

  public Album getById(Long albumId) {
    return albumRepository
        .findById(albumId)
        .orElseThrow(() -> new ResourceNotFound("Album não encontrado!"));
  }

  public boolean existsById(Long albumId) {
    return albumRepository.existsById(albumId);
  }

  @Transactional
  public void deleteById(Long albumId) {
    User loggedUser = SecurityUtils.getLoggedUser();
    albumRepository.findById(albumId)
        .filter(savedAlbum -> savedAlbum.getUser().equals(loggedUser))
        .orElseThrow(() -> new UnauthorizedException("O álbum não pertence ao usuário logado!"));
    albumRepository.deleteById(albumId);
  }

}
