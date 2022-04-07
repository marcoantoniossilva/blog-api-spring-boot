package io.github.marcoantoniossilva.blog.api.controller;

import io.github.marcoantoniossilva.blog.api.assembler.UserAssembler;
import io.github.marcoantoniossilva.blog.api.model.UserDTO;
import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserAssembler userAssembler;

  public UserController(UserService userService, UserAssembler userAssembler) {
    this.userService = userService;
    this.userAssembler = userAssembler;
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
}
