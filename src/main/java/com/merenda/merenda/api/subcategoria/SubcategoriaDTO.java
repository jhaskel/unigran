package com.merenda.merenda.api.subcategoria;


import lombok.Data;
import org.modelmapper.ModelMapper;


@Data

public class SubcategoriaDTO {
    private Long id;
    private Long categoria;
    private String nome;
    private String icone;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;



    public static SubcategoriaDTO create(Subcategoria subcategoria) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(subcategoria, SubcategoriaDTO.class);
    }
}
