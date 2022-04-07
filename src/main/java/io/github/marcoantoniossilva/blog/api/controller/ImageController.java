package io.github.marcoantoniossilva.blog.api.controller;

import io.github.marcoantoniossilva.blog.api.assembler.ImageAssembler;
import io.github.marcoantoniossilva.blog.api.model.ImageInputDTO;
import io.github.marcoantoniossilva.blog.api.model.ImageResponseDTO;
import io.github.marcoantoniossilva.blog.domain.model.Album;
import io.github.marcoantoniossilva.blog.domain.model.Image;
import io.github.marcoantoniossilva.blog.domain.service.AlbumService;
import io.github.marcoantoniossilva.blog.domain.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

  private final ImageService imageService;
  private final AlbumService albumService;
  private final ImageAssembler imageAssembler;

  public ImageController(ImageService imageService, AlbumService albumService, ImageAssembler imageAssembler) {
    this.imageService = imageService;
    this.albumService = albumService;
    this.imageAssembler = imageAssembler;
  }

  @GetMapping
  public List<ImageResponseDTO> list() throws IOException {
    List<Image> images = imageService.listAll();
    return imageAssembler.collectionEntityToCollectionDTO(images);
  }

  @GetMapping("{imageId}")
  public ResponseEntity<byte[]> search(@PathVariable Long imageId){
    Image image = imageService.getById(imageId);

    return ResponseEntity
        .ok()
        .contentType(MediaType.valueOf(image.getType()))
        .body(image.getContent());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ImageResponseDTO add(@ModelAttribute ImageInputDTO imageInputDTO) {
    Long albumId = imageInputDTO.getAlbumId();
    Album album = albumService.getById(albumId);

    Image image = imageAssembler.multipartFileToEntity(imageInputDTO.getImage());
    image.setAlbum(album);

    Image savedImage = imageService.save(image);
    return imageAssembler.entityToDTO(savedImage);
  }

}













