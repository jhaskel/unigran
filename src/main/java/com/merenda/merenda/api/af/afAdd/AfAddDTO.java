package com.merenda.merenda.api.af.afAdd;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AfAddDTO {
    private Long id;
    private Long code;
    private Long fornecedor;
    private String nomefornecedor;
    private Long nivel;
    private String nomenivel;
    private String createdAt;
    private Boolean isautorizado;
    private String status;
    private Boolean isativo;
    private String pedido;
    private Double total;
    private Long despesa;
    private Long despesax;
    private String numero;

    private Long totalAf;


    public static AfAddDTO create(AfAdd afAdd) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(afAdd, AfAddDTO.class);
    }
}
