package io.github.marcoantoniossilva.blog.api.model;

import io.github.marcoantoniossilva.blog.api.model.input.UserIdDTO;

import java.time.LocalDateTime;
import java.util.List;

public class AlbumDTO {

  private Long id;

  private String name;

  private UserIdDTO user;

  private LocalDateTime publicatedAt;

  private List<ImageUriDTO> imageList;

  public AlbumDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserIdDTO getUser() {
    return user;
  }

  public void setUser(UserIdDTO user) {
    this.user = user;
  }

  public void setImageList(List<ImageUriDTO> imageList) {
    this.imageList = imageList;
  }


  public LocalDateTime getPublicatedAt() {
    return publicatedAt;
  }

  public void setPublicatedAt(LocalDateTime publicatedAt) {
    this.publicatedAt = publicatedAt;
  }

  public List<ImageUriDTO> getImageList() {
    return imageList;
  }

  @Override
  public String toString() {
    return "AlbumDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", user=" + user +
        ", publicatedAt=" + publicatedAt +
        ", imageList=" + imageList +
        '}';
  }
}
