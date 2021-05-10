package com.merenda.merenda.api.categorias;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.Transient;

@Data

public class CategoriaDTO {
    private Long id;
    private String nome;
    private String image;
    private Boolean isativo;
    private Boolean isalimento;
    private String createdAt;
    private String modifiedAt;
  /*  @JsonInclude
    private String teste;*/


    public static CategoriaDTO create(Categoria categoria) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(categoria, CategoriaDTO.class);
    }
}
