package io.github.marcoantoniossilva.blog.domain.service;

import io.github.marcoantoniossilva.blog.domain.exception.BusinessException;
import io.github.marcoantoniossilva.blog.domain.exception.ResourceNotFound;
import io.github.marcoantoniossilva.blog.domain.model.User;
import io.github.marcoantoniossilva.blog.domain.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

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

    if (userRepository.existsByEmail(user.getEmail())) {
      throw new BusinessException("Já existe um usuário cadastrado com este email!");
    }

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

  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Transactional
  public void deleteById(Long userId) {
    userRepository.deleteById(userId);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User savedUser = userRepository.findByEmail(email).
        orElseThrow(() -> new ResourceNotFound("Usuário não encontrado!"));

    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE "));
    return new org.springframework.security.core.userdetails.User(savedUser.getEmail(), savedUser.getPassword(), authorities);
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFound("Usuário não encontrado!"));
  }
}
