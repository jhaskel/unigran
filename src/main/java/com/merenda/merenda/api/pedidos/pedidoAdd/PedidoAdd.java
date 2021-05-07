package com.merenda.merenda.api.pedidos.pedidoAdd;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity(name = "pedido")
public class PedidoAdd {

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
    private String createdAt;
    private String modifiedAt;
    private Boolean isativo;
    private Boolean ischeck;
    private Boolean iscart;





}

