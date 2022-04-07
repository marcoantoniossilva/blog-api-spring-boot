package io.github.marcoantoniossilva.blog.api.assembler;
import io.github.marcoantoniossilva.blog.api.model.UserDTO;
import io.github.marcoantoniossilva.blog.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
  public class UserAssembler {

  private final ModelMapper modelMapper;

  public UserAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public UserDTO entityToDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  public List<UserDTO> collectionEntityToCollectionDTO(List<User> users) {
    return users.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }
}