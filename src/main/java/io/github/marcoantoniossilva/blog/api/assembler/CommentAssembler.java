package io.github.marcoantoniossilva.blog.api.assembler;

import io.github.marcoantoniossilva.blog.api.model.CommentDTO;
import io.github.marcoantoniossilva.blog.domain.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentAssembler {

  private final ModelMapper modelMapper;

  public CommentAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public CommentDTO entityToDTO(Comment comment) {
    return modelMapper.map(comment, CommentDTO.class);
  }

  public List<CommentDTO> collectionEntityToCollectionDTO(List<Comment> comments) {
    return comments.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public Comment dtoToEntity(CommentDTO commentDTO) {
    return modelMapper.map(commentDTO, Comment.class);
  }

}
