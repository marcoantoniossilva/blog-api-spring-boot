package io.github.marcoantoniossilva.blog.api.assembler;

import io.github.marcoantoniossilva.blog.api.model.PostDTO;
import io.github.marcoantoniossilva.blog.api.model.PostInputDTO;
import io.github.marcoantoniossilva.blog.domain.model.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostAssembler {

  private final ModelMapper modelMapper;

  public PostAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public PostDTO entityToDTO(Post post) {
    URI imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("images/{id}").buildAndExpand(post.getImage().getId()).toUri();

    PostDTO postDTO = modelMapper.map(post, PostDTO.class);
    postDTO.setImageUri(imageUri);
    return postDTO;
  }

  public List<PostDTO> collectionEntityToCollectionDTO(List<Post> posts) {
    return posts.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public Post dtoToEntity(PostInputDTO postInputDTO) {
    return modelMapper.map(postInputDTO, Post.class);
  }
}
