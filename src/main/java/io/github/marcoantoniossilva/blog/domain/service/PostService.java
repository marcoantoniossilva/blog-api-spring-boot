package io.github.marcoantoniossilva.blog.domain.service;

import io.github.marcoantoniossilva.blog.common.SecurityUtils;
import io.github.marcoantoniossilva.blog.domain.exception.ResourceNotFound;
import io.github.marcoantoniossilva.blog.domain.exception.UnauthorizedException;
import io.github.marcoantoniossilva.blog.domain.model.Post;
import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> listAll() {
    return postRepository.findAll();
  }

  public Optional<Post> findById(Long postId) {
    Optional<Post> post =  postRepository.findById(postId);
    return post;
  }

  public Post getById(Long postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new ResourceNotFound("Post não encontrado!"));
  }

  @Transactional
  public Post save(Post post) {
    post.setUser(SecurityUtils.getLoggedUser());
    return postRepository.save(post);
  }

  public boolean existsById(Long postId) {
    return postRepository.existsById(postId);
  }

  @Transactional
  public void deleteById(Long postId) {
    User loggedUser = SecurityUtils.getLoggedUser();
    postRepository.findById(postId)
        .filter(savedComment -> savedComment.getUser().equals(loggedUser))
        .orElseThrow(() -> new UnauthorizedException("A postagem não pertence ao usuário logado!"));
    postRepository.deleteById(postId);
  }

}
