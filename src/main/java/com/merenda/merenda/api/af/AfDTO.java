package com.merenda.merenda.api.af;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AfDTO {
    private Long id;
    private Long code;
    private Long fornecedor;
    private String status;
    private Boolean isenviado;
    private String createdAt;
    private Boolean isativo;



    public static AfDTO create(Af af) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(af, AfDTO.class);
    }
}
