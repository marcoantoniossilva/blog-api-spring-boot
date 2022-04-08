package io.github.marcoantoniossilva.blog.api.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageInputDTO {

  private Long id;
  private Long albumId;
  private MultipartFile image;

  public ImageInputDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getAlbumId() {
    return albumId;
  }

  public void setAlbumId(Long albumId) {
    this.albumId = albumId;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "ImageInputDTO{" +
        "id=" + id +
        ", album=" + albumId +
        ", image=" + image +
        '}';
  }
}
