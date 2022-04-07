package io.github.marcoantoniossilva.blog.api.model;

import io.github.marcoantoniossilva.blog.api.model.input.PostIdDTO;
import io.github.marcoantoniossilva.blog.api.model.input.UserIdDTO;

import java.time.LocalDateTime;

public class CommentDTO {

  private Long id;
  private String text;
  private UserIdDTO user;
  private PostIdDTO post;
  private LocalDateTime publicatedAt;

  public CommentDTO() {
  }

  public CommentDTO(Long id, String text, UserIdDTO user, PostIdDTO post) {
    this.id = id;
    this.text = text;
    this.user = user;
    this.post = post;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDateTime getPublicatedAt() {
    return publicatedAt;
  }

  public void setPublicatedAt(LocalDateTime publicatedAt) {
    this.publicatedAt = publicatedAt;
  }

  public UserIdDTO getUser() {
    return user;
  }

  public void setUser(UserIdDTO user) {
    this.user = user;
  }

  public PostIdDTO getPost() {
    return post;
  }

  public void setPost(PostIdDTO post) {
    this.post = post;
  }

  @Override
  public String toString() {
    return "CommentDTO{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", user=" + user +
        ", post=" + post +
        ", publicatedAt=" + publicatedAt +
        '}';
  }
}
