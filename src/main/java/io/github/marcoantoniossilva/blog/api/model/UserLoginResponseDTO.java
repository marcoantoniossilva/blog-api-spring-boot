package io.github.marcoantoniossilva.blog.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserLoginResponseDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private String token;
  private LocalDateTime expiresIn;
  private String tokenProvider;

  public UserLoginResponseDTO(String token, LocalDateTime expiresIn, String tokenProvider) {
    this.token = token;
    this.expiresIn = expiresIn;
    this.tokenProvider = tokenProvider;
  }

  public String getToken() {
    return token;
  }

  public LocalDateTime getExpiresIn() {
    return expiresIn;
  }

  public String getTokenProvider() {
    return tokenProvider;
  }
}
