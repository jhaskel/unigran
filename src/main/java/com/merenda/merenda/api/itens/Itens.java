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
    private String cod;
    private String pedido;
    private Long escola;
    private Long nivel;
    private Long produto;
    private String alias;
    private String nomeescola;
    private String nomenivel;
    private String unidade;
    private Long ano;
    private Double quantidade;
    private Double valor;
    private Double total;
    private Long af;
    private String obs;
    private String createdAt;
    private String modifiedAt;
    private Long categoria;
    private Long fornecedor;
    private Boolean ischeck;
    private Boolean isautorizado;
    private Boolean isagro;
    private String status;
    private String mes;
    private Boolean isativo;
    private Long licitacao;
    private String processo;

    private Double tot;
    private String nomec;



}
