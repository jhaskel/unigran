package com.merenda.merenda.api.cardapio.cardapioRefeicao;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CardapioRefeicaoDTO {
    private Long id;
    private Long cardapio;
    private Long refeicao;

    public static CardapioRefeicaoDTO create(CardapioRefeicao cardapioRefeicao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cardapioRefeicao, CardapioRefeicaoDTO.class);
    }
}
