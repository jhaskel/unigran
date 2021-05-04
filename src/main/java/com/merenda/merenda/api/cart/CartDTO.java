package com.merenda.merenda.api.cart;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CartDTO {
    private Long id;
    private Long escola;
    private Long produto;
    private String alias;
    private Double quantidade;
    private Double valor;
    private Double total;
    private String obs;
    private String createdAt;


    public static CartDTO create(Cart cart) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cart, CartDTO.class);
    }
}
