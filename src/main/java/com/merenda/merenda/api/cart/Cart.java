package com.merenda.merenda.api.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long local;
    private Long produto;
    private Long categoria;
    private Long licitacao;
    private Long fornecedor;
    private String unidade;
    private String cod;
    private String alias;
    private Double quantidade;
    private Double valor;
    private Double total;
    private String createdAt;

}

