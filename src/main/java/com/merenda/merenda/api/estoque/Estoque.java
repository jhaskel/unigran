package com.merenda.merenda.api.estoque;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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





}

