package com.merenda.merenda.api.cardapio.cardapioAdd;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CardapioAddDTO {
    private Long id;
    private String semana;
    private String escola;
    private String dia;
    private String periodo;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;

    public static CardapioAddDTO create(CardapioAdd cardapioAdd) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cardapioAdd, CardapioAddDTO.class);
    }
}
