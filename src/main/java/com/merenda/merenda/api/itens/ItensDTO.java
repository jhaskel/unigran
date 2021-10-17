package com.merenda.merenda.api.itens;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItensDTO {
    private Long id;
    private Long local;
    private Long produto;
    private Long pedido;
    private String alias;
    private Long categoria;
    private Long fornecedor;
    private String unidade;
    private Long ano;
    private Long af;
    private String createdAt;
    private Double quantidade;
    private Double valor;
    private Double total;
    private String status;
    private String mes;
    private Boolean isativo;
    private String modifiedAt;

    //join
    private Double tot;
    private String nomec;


    public static ItensDTO create(Itens itens) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(itens, ItensDTO.class);
    }
}
