package io.github.marcoantoniossilva.blog.security;

import io.github.marcoantoniossilva.blog.api.model.UserLoginResponseDTO;
import io.github.marcoantoniossilva.blog.common.TokenConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
public class JwtManager {

  private final TokenConfiguration tokenConfiguration;

  public JwtManager(TokenConfiguration tokenConfiguration) {
    this.tokenConfiguration = tokenConfiguration;
  }

  public UserLoginResponseDTO createToken(String email, List<String> roles) {

    LocalDateTime expiration = LocalDateTime.now().plusMinutes(tokenConfiguration.getExpirationTime().toMinutes());

    Date date = Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant());

    String token = Jwts.builder()
        .setSubject(email)
        .setExpiration(date)
        .claim(tokenConfiguration.getJwtRoleKey(), roles)
        .signWith(SignatureAlgorithm.HS512, tokenConfiguration.getApiKey().getBytes(StandardCharsets.UTF_8))
        .compact();

    return new UserLoginResponseDTO(token, expiration, tokenConfiguration.getJwtProvider());
  }

  public Claims parseToken(String token) throws JwtException {
    return Jwts.parser()
        .setSigningKey(tokenConfiguration.getApiKey().getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(token)
        .getBody();
  }
}
