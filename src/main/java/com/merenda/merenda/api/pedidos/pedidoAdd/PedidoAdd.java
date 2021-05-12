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
    private Long escola;
    private Double total;
    private String status;
    private Boolean isaf;
    private String createdAt;
    private String modifiedAt;
    private Boolean isativo;





}

