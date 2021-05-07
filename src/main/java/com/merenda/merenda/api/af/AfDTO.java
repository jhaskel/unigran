package com.merenda.merenda.api.af;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AfDTO {
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


    public static AfDTO create(Af af) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(af, AfDTO.class);
    }
}
