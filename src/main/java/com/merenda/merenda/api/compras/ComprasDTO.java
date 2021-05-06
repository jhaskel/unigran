package com.merenda.merenda.api.compras;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ComprasDTO {
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
    private String created;
    private Long categoria;
    private Long fornecedor;
    private Boolean ischeck;
    private Boolean isautorizado;
    private Boolean isagro;
    private String status;
    private String mes;
    private Boolean ativo;
    private Long licitacao;
    private String processo;

    private Double tot;


    public static ComprasDTO create(Compras compras) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(compras, ComprasDTO.class);
    }
}
