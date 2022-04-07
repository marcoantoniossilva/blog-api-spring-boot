package io.github.marcoantoniossilva.blog.common;

import io.github.marcoantoniossilva.blog.api.model.ImageResponseDTO;
import io.github.marcoantoniossilva.blog.domain.model.Image;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addMappings(new PropertyMap<ImageResponseDTO, Image>() {
      @Override
      protected void configure() {
        skip(destination.getPost());
      }
    });
    return new ModelMapper();
  }

}