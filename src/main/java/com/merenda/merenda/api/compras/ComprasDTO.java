package com.merenda.merenda.api.compras;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ComprasDTO {
    private Long id;
    private Long escola;
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

    private Double tot;


    public static ComprasDTO create(Compras compras) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(compras, ComprasDTO.class);
    }
}
