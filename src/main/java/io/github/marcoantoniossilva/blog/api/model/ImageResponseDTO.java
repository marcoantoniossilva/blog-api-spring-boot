package io.github.marcoantoniossilva.blog.api.model;

import io.github.marcoantoniossilva.blog.api.model.input.AlbumIdDTO;


public class ImageResponseDTO {

  private Long id;
  private String type;
  private AlbumIdDTO album;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AlbumIdDTO getAlbum() {
    return album;
  }

  public void setAlbum(AlbumIdDTO album) {
    this.album = album;
  }

  @Override
  public String toString() {
    return "ImageResponseDTO{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", album=" + album +
        '}';
  }
}
