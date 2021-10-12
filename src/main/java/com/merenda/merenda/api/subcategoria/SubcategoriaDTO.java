package com.merenda.merenda.api.subcategoria;


import lombok.Data;
import org.modelmapper.ModelMapper;


@Data

public class SubcategoriaDTO {
    private Long id;
    private String nome;
    private String icone;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;

  /*  @JsonInclude
    private String teste;*/


    public static SubcategoriaDTO create(Subcategoria subcategoria) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subcategoria, SubcategoriaDTO.class);
    }
}
