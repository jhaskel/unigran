package com.merenda.merenda.api.af.afAdd;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AfAddDTO {
    private Long id;
    private Long code;
    private Long nivel;
    private Long setor;
    private Long fornecedor;
    private String status;
    private Boolean isenviado;
    private String createdAt;
    private Boolean isativo;



    public static AfAddDTO create(AfAdd afAdd) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(afAdd, AfAddDTO.class);
    }
}
