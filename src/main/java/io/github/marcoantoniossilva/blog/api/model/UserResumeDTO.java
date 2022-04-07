package io.github.marcoantoniossilva.blog.api.model;

public class UserResumeDTO {

  private String name;

  public UserResumeDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UserResumeDTO{" +
        "name='" + name + '\'' +
        '}';
  }
}
