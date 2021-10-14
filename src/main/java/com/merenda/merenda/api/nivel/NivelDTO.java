package com.merenda.merenda.api.nivel;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class NivelDTO {
    private Long id;
    private Long setor;
    private String nome;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;
    private Long modifiedBy;

    public static NivelDTO create(Nivel nivel) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(nivel, NivelDTO.class);
    }
}
