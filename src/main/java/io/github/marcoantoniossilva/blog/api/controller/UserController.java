package io.github.marcoantoniossilva.blog.api.controller;

import io.github.marcoantoniossilva.blog.api.assembler.UserAssembler;
import io.github.marcoantoniossilva.blog.api.model.UserDTO;
import io.github.marcoantoniossilva.blog.api.model.UserLoginDTO;
import io.github.marcoantoniossilva.blog.api.model.UserLoginResponseDTO;
import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.service.UserService;
import io.github.marcoantoniossilva.blog.security.JwtManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserAssembler userAssembler;
  private final AuthenticationManager authManager;
  private final JwtManager jwtManager;

  public UserController(UserService userService, UserAssembler userAssembler, AuthenticationManager authManager, JwtManager jwtManager) {
    this.userService = userService;
    this.userAssembler = userAssembler;
    this.authManager = authManager;
    this.jwtManager = jwtManager;
  }

  @GetMapping
  public List<UserDTO> list() {
    List<User> users = userService.listAll();
    return userAssembler.collectionEntityToCollectionDTO(users);
  }

  @GetMapping("{userId}")
  public ResponseEntity<UserDTO> search(@PathVariable Long userId) {
    return userService.findById(userId)
        .map(user ->
            ResponseEntity.ok(userAssembler.entityToDTO(user))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDTO add(@RequestBody User user) {
    User savedUser = userService.save(user);
    return userAssembler.entityToDTO(savedUser);
  }

  @PutMapping("{userId}")
  public ResponseEntity<UserDTO> update(@PathVariable Long userId, @RequestBody User user) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    user.setId(userId);
    User savedUser = userService.save(user);
    return ResponseEntity.ok(userAssembler.entityToDTO(savedUser));
  }

  @DeleteMapping("{userId}")
  public ResponseEntity<Void> delete(@PathVariable Long userId) {
    if (!userService.existsById(userId)) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteById(userId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("login")
  public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
    UsernamePasswordAuthenticationToken token =
        new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword());

    Authentication authentication = authManager.authenticate(token);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    org.springframework.security.core.userdetails.User userDetails =
        (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

    String email = userDetails.getUsername();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    return ResponseEntity.ok(jwtManager.createToken(email, roles));
  }
}
