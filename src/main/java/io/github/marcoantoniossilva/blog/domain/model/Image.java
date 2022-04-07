package io.github.marcoantoniossilva.blog.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "file_name")
  private String fileName;

  @OneToOne
  private Post post;

  @ManyToOne
  private Album album;

  private byte[] content;
  private String type;

  public Image() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getContent() {
    return content;
  }

  public void setContent(byte[] content) {
    this.content = content;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  @Override
  public String toString() {
    return "Image{" +
        "id=" + id +
        ", fileName='" + fileName + '\'' +
        ", post=" + post +
        ", album=" + album +
        ", type='" + type + '\'' +
        '}';
  }
}
