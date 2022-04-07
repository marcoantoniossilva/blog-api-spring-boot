package io.github.marcoantoniossilva.blog.domain.service;

import io.github.marcoantoniossilva.blog.common.SecurityUtils;
import io.github.marcoantoniossilva.blog.domain.exception.ResourceNotFound;
import io.github.marcoantoniossilva.blog.domain.exception.UnauthorizedException;
import io.github.marcoantoniossilva.blog.domain.model.Comment;
import io.github.marcoantoniossilva.blog.domain.model.Post;
import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final PostService postService;

  public CommentService(CommentRepository commentRepository, PostService postService) {
    this.commentRepository = commentRepository;
    this.postService = postService;
  }

  public List<Comment> listAll() {
    return commentRepository.findAll();
  }

  public Optional<Comment> findById(Long commentId) {
    return commentRepository.findById(commentId);
  }

  public List<Comment> findAllByPostId(Long postId) {
    return commentRepository.findAllByPostId(postId);
  }

  @Transactional
  public Comment save(Comment comment) {
    Post post = postService
        .findById(comment.getPost().getId())
        .orElseThrow(() -> new ResourceNotFound("Postagem não encontrada!"));


    comment.setUser(SecurityUtils.getLoggedUser());
    comment.setPost(post);

    return commentRepository.save(comment);
  }

  public boolean existsById(Long commentId) {
    return commentRepository.existsById(commentId);
  }

  @Transactional
  public void deleteById(Long commentId) {
    User loggedUser = SecurityUtils.getLoggedUser();
    commentRepository.findById(commentId)
        .filter(savedComment -> savedComment.getUser().equals(loggedUser))
        .orElseThrow(() -> new UnauthorizedException("O comentário não pertence ao autor logado!"));
    commentRepository.deleteById(commentId);
  }

}