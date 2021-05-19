package com.merenda.merenda.api.refeicao;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class RefeicaoDTO {
    private Long id;
    private String nome;
    private String imagem;
    private Long kcal;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;

    public static RefeicaoDTO create(Refeicao refeicao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(refeicao, RefeicaoDTO.class);
    }
}
