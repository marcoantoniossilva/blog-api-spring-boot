package io.github.marcoantoniossilva.blog.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @ManyToOne
  private User user;

  @Column(name = "publicated_at")
  private LocalDateTime publicatedAt;

  @OneToMany(mappedBy = "album")
  private List<Image> imageList;

  public Album() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPublicatedAt(LocalDateTime publicatedAt) {
    this.publicatedAt = publicatedAt;
  }

  public List<Image> getImagesList() {
    return imageList;
  }

  public void setImageList(List<Image> imageList) {
    this.imageList = imageList;
  }

  @PrePersist
  public void prePersist(){
    this.publicatedAt = LocalDateTime.now();
  }

  @Override
  public String toString() {
    return "Album{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", user=" + user +
        ", publicatedAt=" + publicatedAt +
        ", imageList=" + imageList +
        '}';
  }
}
