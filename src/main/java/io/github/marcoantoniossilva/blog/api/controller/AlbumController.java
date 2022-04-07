package io.github.marcoantoniossilva.blog.api.controller;

import io.github.marcoantoniossilva.blog.api.assembler.AlbumAssembler;
import io.github.marcoantoniossilva.blog.api.model.AlbumDTO;
import io.github.marcoantoniossilva.blog.domain.model.Album;
import io.github.marcoantoniossilva.blog.domain.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

  private final AlbumService albumService;
  private final AlbumAssembler albumAssembler;

  public AlbumController(AlbumService albumService, AlbumAssembler albumAssembler) {
    this.albumService = albumService;
    this.albumAssembler = albumAssembler;
  }

  @GetMapping
  public List<AlbumDTO> list() {
    List<Album> albums = albumService.listAll();
    return albumAssembler.collectionEntityToCollectionDTO(albums);
  }

  @GetMapping("{albumId}")
  public ResponseEntity<AlbumDTO> search(@PathVariable Long albumId) {
    return albumService.findById(albumId)
        .map(comment ->
            ResponseEntity.ok(albumAssembler.entityToDTO(comment))
        )
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AlbumDTO add(@RequestBody AlbumDTO albumDTO) {
    Album album = albumAssembler.dtoToEntity(albumDTO);
    Album savedAlbum = albumService.save(album);
    return albumAssembler.entityToDTO(savedAlbum);
  }

  @DeleteMapping("{albumId}")
  public ResponseEntity<Void> delete(@PathVariable Long albumId) {
    if (!albumService.existsById(albumId)) {
      return ResponseEntity.notFound().build();
    }
    albumService.deleteById(albumId);
    return ResponseEntity.noContent().build();
  }

}
