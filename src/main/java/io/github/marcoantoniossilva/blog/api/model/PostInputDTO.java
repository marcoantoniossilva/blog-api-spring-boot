package io.github.marcoantoniossilva.blog.api.model;

import org.springframework.web.multipart.MultipartFile;

public class PostInputDTO {

  private Long id;
  private String title;
  private String text;
  private MultipartFile image;

  public PostInputDTO() {
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

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "PostInputDTO{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", image=" + image +
        '}';
  }
}
