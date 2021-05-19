package com.merenda.merenda.api.cardapio;

import com.merenda.merenda.api.refeicao.Refeicao;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class CardapioDTO {
    private Long id;
    private String semana;
    private String escola;
    private String dia;
    private String periodo;
    private String pratos;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;

    public static CardapioDTO create(Cardapio cardapio) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cardapio, CardapioDTO.class);
    }
}
