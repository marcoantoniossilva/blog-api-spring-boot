package io.github.marcoantoniossilva.blog.domain.service;

import io.github.marcoantoniossilva.blog.domain.exception.ResourceNotFound;
import io.github.marcoantoniossilva.blog.domain.model.Image;
import io.github.marcoantoniossilva.blog.domain.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

  private final ImageRepository imageRepository;
  private final PostService postService;

  public ImageService(ImageRepository imageRepository, PostService postService) {
    this.imageRepository = imageRepository;
    this.postService = postService;
  }

  public List<Image> listAll() {
    return imageRepository.findAll();
  }

  public List<Image> listAllByPostId(Long postId) {
    postService
        .findById(postId)
        .orElseThrow(() -> new ResourceNotFound("Postagem não encontrada!"));

    return imageRepository.findAllByPostId(postId);
  }

  public URI getUriByPostId(Long postId) {
    postService
        .findById(postId)
        .orElseThrow(() -> new ResourceNotFound("Postagem não encontrada!"));

    return imageRepository.getUriByPostId(postId);
  }

  public Optional<Image> findById(Long imageId) {
    return imageRepository.findById(imageId);
  }

  public Image getById(Long imageId) {
    return imageRepository.findById(imageId)
        .orElseThrow(() -> new ResourceNotFound("Imagem não encontrada!"));
  }

  public Image save(Image image) {
    return imageRepository.save(image);
  }

  public boolean existsById(Long imageId) {
    return imageRepository.existsById(imageId);
  }

  @Transactional
  public void deleteById(Long imageId) {
    imageRepository.deleteById(imageId);
  }
}
