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
    private Long escola;
    private Long produto;
    private Long pedido;
    private Long categoria;
    private Long fornecedor;
    private Long ano;
    private Long af;
    private Double quantidade;
    private Double valor;
    private Double total;
    private String alias;
    private String unidade;
    private String mes;
    private String status;
    private String createdAt;
    private String modifiedAt;
    private Boolean isativo;

    //join
    private Double tot;
    private String nomec;


}

