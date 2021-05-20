package com.merenda.merenda.api.cardapio;

import com.merenda.merenda.api.refeicao.Refeicao;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class CardapioDTO {
    private Long id;
    private Long escola;
    private String nomedaescola;
    private String tipo;
    private String imagem;
    private String title;
    private String content;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;

    public static CardapioDTO create(Cardapio cardapio) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cardapio, CardapioDTO.class);
    }
}
