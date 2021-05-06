package com.merenda.merenda.api.pedidos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Long escola;
    private String nomeescola;
    private Long nivel;
    private Double total;
    private Long comprador;
    private String status;
    private Long semana;
    private Boolean isaf;
    private String created;
    private String modified;
    private Boolean ativo;
    private Boolean ischeck;
    private Boolean iscart;






}

