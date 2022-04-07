package io.github.marcoantoniossilva.blog.api.model;

import java.net.URI;

public class ImageUriDTO {

  private URI uri;

  public ImageUriDTO() {
  }

  public ImageUriDTO(URI uri) {
    this.uri = uri;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }
}
