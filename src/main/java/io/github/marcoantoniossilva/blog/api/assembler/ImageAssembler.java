package io.github.marcoantoniossilva.blog.api.assembler;

import io.github.marcoantoniossilva.blog.api.model.ImageResponseDTO;
import io.github.marcoantoniossilva.blog.domain.exception.ResourceNotFound;
import io.github.marcoantoniossilva.blog.domain.model.Image;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageAssembler {

  private final ModelMapper modelMapper;

  public ImageAssembler(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public ImageResponseDTO entityToDTO(Image image) {
    return modelMapper.map(image, ImageResponseDTO.class);
  }

  public List<ImageResponseDTO> collectionEntityToCollectionDTO(List<Image> images) {
    return images.stream()
        .map(this::entityToDTO)
        .collect(Collectors.toList());
  }

  public Image multipartFileToEntity(MultipartFile image) {
    Image newImage = new Image();
    newImage.setFileName(image.getOriginalFilename());
    newImage.setType(image.getContentType());
    try {
      newImage.setContent(image.getBytes());
    } catch (IOException e) {
      throw new ResourceNotFound("Imagem não anexada!");
    }
    return newImage;
  }
}
