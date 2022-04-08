package io.github.marcoantoniossilva.blog.api.controller;

import io.github.marcoantoniossilva.blog.api.assembler.ImageAssembler;
import io.github.marcoantoniossilva.blog.api.assembler.PostAssembler;
import io.github.marcoantoniossilva.blog.api.model.PostDTO;
import io.github.marcoantoniossilva.blog.api.model.PostInputDTO;
import io.github.marcoantoniossilva.blog.domain.model.Image;
import io.github.marcoantoniossilva.blog.domain.model.Post;
import io.github.marcoantoniossilva.blog.domain.service.ImageService;
import io.github.marcoantoniossilva.blog.domain.service.PostService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")

public class PostController {

  private final PostService postService;
  private final PostAssembler postAssembler;
  private final ImageService imageService;
  private final ImageAssembler imageAssembler;

  public PostController(PostService postService, PostAssembler postAssembler, ImageService imageService, ImageAssembler imageAssembler) {
    this.postService = postService;
    this.postAssembler = postAssembler;
    this.imageService = imageService;
    this.imageAssembler = imageAssembler;
  }

  @GetMapping
  public List<PostDTO> list() {
    List<Post> posts = postService.listAll();
    return postAssembler.collectionEntityToCollectionDTO(posts);
  }

  @GetMapping("{postId}")
  public ResponseEntity<PostDTO> search(@PathVariable Long postId) {
    return postService.findById(postId)
        .map(post ->
            ResponseEntity.ok(postAssembler.entityToDTO(post))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping(
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public PostDTO add(@ModelAttribute PostInputDTO postInputDTO) {
    Post post = postAssembler.dtoToEntity(postInputDTO);
    Image image = imageAssembler.multipartFileToEntity(postInputDTO.getImage());

    image.setPost(post);
    post.setImage(image);

    Post savedPost = postService.save(post);
    imageService.save(image);

    return postAssembler.entityToDTO(savedPost);
  }

  @DeleteMapping("{postId}")
  public ResponseEntity<Void> delete(@PathVariable Long postId) {
    // TODO delete cascade?
    if (!postService.existsById(postId)) {
      return ResponseEntity.notFound().build();
    }
    postService.deleteById(postId);
    return ResponseEntity.noContent().build();
  }
}
