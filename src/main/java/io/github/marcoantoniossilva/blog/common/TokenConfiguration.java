package io.github.marcoantoniossilva.blog.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;

@Configuration
@PropertySource("classpath:application.properties")
public class TokenConfiguration {

  @Value("${api.token.expiration-time}")
  public Duration expirationTime;

  @Value("${api.token.api-key}")
  public String apiKey;

  @Value("${api.token.jwt-provider}")
  public String jwtProvider;

  @Value("${api.token.jwt-role-key}")
  public String jwtRoleKey;

  public Duration getExpirationTime() {
    return expirationTime;
  }

  public String getApiKey() {
    return apiKey;
  }

  public String getJwtProvider() {
    return jwtProvider;
  }

  public String getJwtRoleKey() {
    return jwtRoleKey;
  }
}