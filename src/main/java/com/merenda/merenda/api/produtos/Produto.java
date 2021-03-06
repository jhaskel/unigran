package com.merenda.merenda.api.produtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nome;
    private String alias;
    private String unidade;
    private Long fornecedor;
    private String image;
    private String createdAt;
    private String modifiedAt;





}

