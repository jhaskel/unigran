package com.merenda.merenda.api.cart;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CartDTO {
    private Long id;
    private Long local;
    private Long produto;
    private Long fornecedor;
    private Long categoria;
    private Long licitacao;
    private String unidade;
    private String cod;
    private String alias;
    private Double quantidade;
    private Double valor;
    private Double total;
    private String createdAt;

    public static CartDTO create(Cart cart) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cart, CartDTO.class);
    }
}
