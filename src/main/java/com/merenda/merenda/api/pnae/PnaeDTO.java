package com.merenda.merenda.api.pnae;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PnaeDTO {
    private Long id;
    private Double valor;
    private Long ano;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;
    //
    private Double soma;

    public static PnaeDTO create(Pnae pnae) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pnae, PnaeDTO.class);
    }
}
