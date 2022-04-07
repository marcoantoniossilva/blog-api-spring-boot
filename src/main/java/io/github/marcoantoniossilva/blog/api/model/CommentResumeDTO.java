package io.github.marcoantoniossilva.blog.api.model;

import io.github.marcoantoniossilva.blog.api.model.input.UserIdDTO;

public class CommentResumeDTO {

  private Long id;
  private String text;
  private UserIdDTO user;

  public CommentResumeDTO() {
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

  public UserIdDTO getUser() {
    return user;
  }

  public void setUser(UserIdDTO user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "CommentResumeDTO{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", user=" + user +
        '}';
  }
}
