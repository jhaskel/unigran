package com.merenda.merenda.api.config;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ConfigDTO {
    private Long id;
    private String entidade;
    private String setor;
    private String nomeContato;
    private String cargo;
    private String email;
    private String celular;
    private Boolean ativo;
    private String created;
    private String modified;


    public static ConfigDTO create(Config config) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(config, ConfigDTO.class);
    }
}
