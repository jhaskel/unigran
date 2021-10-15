package com.merenda.merenda.api.produtos;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
public class ProdutoDTO {
    private Long id;
    private String code;
    private String nome;
    private String alias;
    private String unidade;
    private Long categoria;
    private Long subcategoria;
    private Long fornecedor;
    private String image;
    private String createdAt;
    private String modifiedAt;

    public static ProdutoDTO create(Produto produto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(produto, ProdutoDTO.class);
    }
}
