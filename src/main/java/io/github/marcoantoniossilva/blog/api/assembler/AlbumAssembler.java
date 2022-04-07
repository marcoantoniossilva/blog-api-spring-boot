package io.github.marcoantoniossilva.blog.api.assembler;

import io.github.marcoantoniossilva.blog.api.model.AlbumDTO;
import io.github.marcoantoniossilva.blog.api.model.ImageUriDTO;
import io.github.marcoantoniossilva.blog.domain.model.Album;
import io.github.marcoantoniossilva.blog.domain.model.Image;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumAssembler {

  private final ModelMapper modelMapper;

  public AlbumAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public AlbumDTO entityToDTO(Album album) {
    AlbumDTO albumDTO = modelMapper.map(album, AlbumDTO.class);

    if (album.getImagesList() != null) {
      List<ImageUriDTO> imagesUriDTOs = album.getImagesList().stream()
          .map(Image::getId)
          .map(albumId -> ServletUriComponentsBuilder.fromCurrentContextPath()
              .path("images/{id}").buildAndExpand(albumId).toUri())
          .map(ImageUriDTO::new)
          .collect(Collectors.toList());

      albumDTO.setImageList(imagesUriDTOs);
    }

    return albumDTO;
  }

  public List<AlbumDTO> collectionEntityToCollectionDTO(List<Album> albums) {
    return albums.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public Album dtoToEntity(AlbumDTO albumDTO) {
    return modelMapper.map(albumDTO, Album.class);
  }

}
