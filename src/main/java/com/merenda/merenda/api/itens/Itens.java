package com.merenda.merenda.api.itens;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long escola;
    private Long produto;
    private Long pedido;
    private String alias;
    private Long categoria;
    private Long fornecedor;
    private String unidade;
    private Long ano;
    private Long af;
    private String createdAt;
    private Double quantidade;
    private Double valor;
    private Double total;
    private String status;
    private String mes;
    private Boolean isativo;
    private String modifiedAt;

    private Double tot;
    private String nomec;



}

