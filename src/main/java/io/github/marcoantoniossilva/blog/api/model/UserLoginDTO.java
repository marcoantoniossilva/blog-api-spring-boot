package io.github.marcoantoniossilva.blog.api.model;

public class UserLoginDTO {

  private Long id;
  private String email;
  private String password;

  public UserLoginDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
