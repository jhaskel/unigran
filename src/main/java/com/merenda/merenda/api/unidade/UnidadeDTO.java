package com.merenda.merenda.api.unidade;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UnidadeDTO {
    private Long id;
    private Long nivelescolar;
    private String nome;
    private String alias;
    private String endereco;
    private String bairro;
    private Long alunos;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;

    private Double quant;

    public static UnidadeDTO create(Unidade unidade) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(unidade, UnidadeDTO.class);
    }
}
