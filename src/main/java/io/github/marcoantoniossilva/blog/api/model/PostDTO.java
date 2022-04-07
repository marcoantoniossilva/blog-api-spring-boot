package io.github.marcoantoniossilva.blog.api.model;

import io.github.marcoantoniossilva.blog.api.model.input.UserIdDTO;

import java.net.URI;
import java.time.LocalDateTime;

public class PostDTO {

  private Long id;
  private String title;
  private String text;
  private UserIdDTO user;
  private LocalDateTime publicatedAt;
  private URI imageUri;

  public PostDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public UserIdDTO getUser() {
    return user;
  }

  public void setUser(UserIdDTO user) {
    this.user = user;
  }

  public URI getImageUri() {
    return imageUri;
  }

  public void setImageUri(URI imageUri) {
    this.imageUri = imageUri;
  }

  public LocalDateTime getPublicatedAt() {
    return publicatedAt;
  }

  public void setPublicatedAt(LocalDateTime publicatedAt) {
    this.publicatedAt = publicatedAt;
  }
}
