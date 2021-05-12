package com.merenda.merenda.api.produtos;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class ProdutoDTO {
    private Long id;
    private Long categoria;
    private Long fornecedor;
    private String code;
    private String nome;
    private String alias;
    private Double quantidade;
    private Double estoque;
    private Double valor;
    private String unidade;
    private String image;
    private Boolean agrofamiliar;
    private Long ano;
    private Boolean isativo;
    private String createdAt;
    private String modifiedAt;
    private String processo;

    public static ProdutoDTO create(Produto produto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(produto, ProdutoDTO.class);
    }
}
