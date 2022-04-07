package io.github.marcoantoniossilva.blog.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String text;

  @ManyToOne
  private User user;

  @ManyToOne
  private Post post;

  @Column(name = "publicated_at")
  private LocalDateTime publicatedAt;

  public Comment() {
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getPublicatedAt() {
    return publicatedAt;
  }

  public void setPublicatedAt(LocalDateTime publicatedAt) {
    this.publicatedAt = publicatedAt;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  @PrePersist
  public void prePersist(){
    this.publicatedAt = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", author=" + user +
        ", post=" + post +
        ", publicatedAt=" + publicatedAt +
        '}';
  }
}
