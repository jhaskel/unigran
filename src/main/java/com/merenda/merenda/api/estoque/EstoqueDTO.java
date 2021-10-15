package com.merenda.merenda.api.estoque;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class EstoqueDTO {
    private Long id;
    private Long produto;
    private Long setor;
    private String code;
    private String alias;
    private Double quantidade;
    private String unidade;
    private Long categoria;
    private Long subcategoria;
    private Long fornecedor;
    private String image;
    private Boolean agrofamiliar;
    private Double valor;
    private Long ano;
    private String createdAt;
    private Boolean isativo;
    private String modifiedAt;
    private String processo;
    //join
    private Double comprado;

    public static EstoqueDTO create(Estoque estoque) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(estoque, EstoqueDTO.class);
    }
}
