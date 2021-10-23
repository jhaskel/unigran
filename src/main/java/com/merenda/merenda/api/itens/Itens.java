package com.merenda.merenda.api.itens;

import com.merenda.merenda.api.fornecedor.Fornecedor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long local;
    private Long nivel;
    private Long setor;
    private Long produto;
    private Long pedido;
    private String alias;
    private Long categoria;
    private Long licitacao;
    private Long fornecedor;
    private String unidade;
    private Long ano;
    private Long af;
    private Double quantidade;
    private String createdAt;
    private Double valor;
    private Double total;
    private String mes;
    private String status;
    private String modifiedAt;
    private Boolean isativo;

    //join
    private Double tot;
    private String nomec;


}

