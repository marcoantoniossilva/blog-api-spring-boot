package io.github.marcoantoniossilva.blog.domain.service;

import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<User> listAll() {
    return userRepository.findAll();
  }

  public Optional<User> findById(Long userId) {
    return userRepository.findById(userId);
  }

  @Transactional
  public User save(User user) {

    if (user.getId() == null) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
    } else {
      user.setPassword(userRepository.getById(user.getId()).getPassword());
    }

    return userRepository.save(user);
  }

  public boolean existsById(Long userId) {
    return userRepository.existsById(userId);
  }

  @Transactional
  public void deleteById(Long userId) {
    userRepository.deleteById(userId);
  }

}
