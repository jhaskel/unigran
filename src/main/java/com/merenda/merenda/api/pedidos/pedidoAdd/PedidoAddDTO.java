package com.merenda.merenda.api.pedidos.pedidoAdd;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PedidoAddDTO {
    private Long id;
    private String code;
    private String nomeescola;
    private Long escola;
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

    private Long totalCart;
    private Long temCart;
    private String nomedaescola;



    public static PedidoAddDTO create(PedidoAdd pedido) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pedido, PedidoAddDTO.class);
    }
}
