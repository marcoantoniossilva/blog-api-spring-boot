package io.github.marcoantoniossilva.blog.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;

  private String text;

  @ManyToOne
  private User user;

  @Column(name = "publicated_at")
  private LocalDateTime publicatedAt;

  @OneToOne(mappedBy = "post", cascade = CascadeType.ALL,orphanRemoval = true)
  private Image image;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public LocalDateTime getPublicatedAt() {
    return publicatedAt;
  }

  public void setPublicatedAt(LocalDateTime publicatedAt) {
    this.publicatedAt = publicatedAt;
  }

  @PrePersist
  public void prePersist(){
    this.publicatedAt = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", user=" + user +
        ", publicatedAt=" + publicatedAt +
        ", image=" + image +
        '}';
  }
}
