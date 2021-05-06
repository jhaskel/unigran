package com.merenda.merenda.api.itens;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ItensDTO {
    private Long id;
    private String cod;
    private String pedido;
    private Long escola;
    private Long nivel;
    private Long produto;
    private String alias;
    private String nomeescola;
    private String nomenivel;
    private String unidade;
    private Long ano;
    private Double quantidade;
    private Double valor;
    private Double total;
    private Long af;
    private String obs;
    private String createdAt;
    private String modifiedAt;
    private Long categoria;
    private Long fornecedor;
    private Boolean ischeck;
    private Boolean isautorizado;
    private Boolean isagro;
    private String status;
    private String mes;
    private Boolean isativo;
    private Long licitacao;
    private String processo;

    private Double tot;
    private Double totAgro;
    private Double tots;
    private Double totalPedido;
    private String nomec;


    public static ItensDTO create(Itens itens) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(itens, ItensDTO.class);
    }
}
