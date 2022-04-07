package io.github.marcoantoniossilva.blog.common;

import io.github.marcoantoniossilva.blog.domain.model.User;

public class SecurityUtils {

  private static User loggedUser;

  public static User getLoggedUser() {
    User user = new User();
    user.setId(3L);
    return user;
  }

  public static void setLoggedUser(User loggedUser) {
    SecurityUtils.loggedUser = loggedUser;
  }
}
