package com.merenda.merenda.api.nivelEscolar;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class NivelEscolarDTO {
    private Long id;
    private String nome;
    private Boolean ativo;
    private String created;
    private String modified;

    public static NivelEscolarDTO create(NivelEscolar nivelEscolar) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(nivelEscolar, NivelEscolarDTO.class);
    }
}
