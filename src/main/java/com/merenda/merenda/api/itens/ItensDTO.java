package com.merenda.merenda.api.itens;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItensDTO {
    private Long id;
    private Long local;
    private Long nivel;
    private Long setor;
    private Long produto;
    private Long pedido;
    private String alias;
    private Long categoria;
    private Long licitacao;
    private Long fornecedor;
    private String unidade;
    private Long ano;
    private Long af;
    private Double quantidade;
    private String createdAt;
    private Double valor;
    private Double total;
    private String mes;
    private String status;
    private String modifiedAt;
    private Boolean isativo;

    //join
    private Double tot;
    private String nomec;


    public static ItensDTO create(Itens itens) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(itens, ItensDTO.class);
    }
}
