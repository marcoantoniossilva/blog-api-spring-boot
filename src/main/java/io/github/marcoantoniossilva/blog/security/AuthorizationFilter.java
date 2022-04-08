package io.github.marcoantoniossilva.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.marcoantoniossilva.blog.common.TokenConfiguration;
import io.github.marcoantoniossilva.blog.domain.exception.AuthenticationException;
import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationFilter extends OncePerRequestFilter {

  private final TokenConfiguration tokenConfiguration;
  private final JwtManager jwtManager;
  private final UserService userService;

  public AuthorizationFilter(TokenConfiguration tokenConfiguration, JwtManager jwtManager, UserService userService) {
    this.tokenConfiguration = tokenConfiguration;
    this.jwtManager = jwtManager;
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String receivedToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (receivedToken == null ||
        receivedToken.isEmpty() ||
        receivedToken.isBlank() ||
        !receivedToken.startsWith(tokenConfiguration.getJwtProvider())) {

      sendError(response);
      return;
    }

    try {
      String token = receivedToken.replace(tokenConfiguration.getJwtProvider(), "");
      Claims claims = jwtManager.parseToken(token);
      String email = claims.getSubject();
      User user = userService.getUserByEmail(email);
      SecurityUtils.setLoggedUser(user);

      List<String> roles = (List<String>) claims.get(tokenConfiguration.getJwtRoleKey());

      List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream()
          .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

      Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, simpleGrantedAuthorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);

    } catch (Exception ex) {
      sendError(response);
      return;
    }

    filterChain.doFilter(request, response);
  }

  public void sendError(HttpServletResponse response) throws IOException {
    AuthenticationException apiError = new AuthenticationException(
        HttpStatus.UNAUTHORIZED.value(),
        "Token invalido ou ausente!",
        LocalDateTime.now().toString());

    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    PrintWriter writer = response.getWriter();
    ObjectMapper mapper = new ObjectMapper();
    String apiErrorString = mapper.writeValueAsString(apiError);

    writer.write(apiErrorString);
    writer.flush();
  }
}
