package com.merenda.merenda.api.setor;


import lombok.Data;
import org.modelmapper.ModelMapper;


@Data

public class SetorDTO {
    private Long id;
    private String nome;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;



    public static SetorDTO create(Setor setor) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(setor, SetorDTO.class);
    }
}
