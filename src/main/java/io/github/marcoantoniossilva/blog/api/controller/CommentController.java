package io.github.marcoantoniossilva.blog.api.controller;

import io.github.marcoantoniossilva.blog.api.assembler.CommentAssembler;
import io.github.marcoantoniossilva.blog.api.model.CommentDTO;
import io.github.marcoantoniossilva.blog.domain.model.Comment;
import io.github.marcoantoniossilva.blog.domain.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;
  private final CommentAssembler commentAssembler;

  public CommentController(CommentService commentService, CommentAssembler commentAssembler) {
    this.commentService = commentService;
    this.commentAssembler = commentAssembler;
  }

  @GetMapping
  public List<CommentDTO> list() {
    List<Comment> comments = commentService.listAll();
    return commentAssembler.collectionEntityToCollectionDTO(comments);
  }

  @GetMapping("{commentId}")
  public ResponseEntity<CommentDTO> search(@PathVariable Long commentId) {
    return commentService.findById(commentId)
        .map(comment ->
            ResponseEntity.ok(commentAssembler.entityToDTO(comment))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("bypost/{postId}")
  public List<CommentDTO> byPost(@PathVariable Long postId) {
    return commentService.findAllByPostId(postId).stream()
        .map(commentAssembler::entityToDTO)
        .collect(Collectors.toList());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentDTO add(@RequestBody CommentDTO commentDTO) {
    Comment comment = commentAssembler.dtoToEntity(commentDTO);
    Comment savedComment = commentService.save(comment);
    return commentAssembler.entityToDTO(savedComment);
  }

  @DeleteMapping("{commentId}")
  public ResponseEntity<Void> delete(@PathVariable Long commentId) {
    if (!commentService.existsById(commentId)) {
      return ResponseEntity.notFound().build();
    }
    commentService.deleteById(commentId);
    return ResponseEntity.noContent().build();
  }
}
