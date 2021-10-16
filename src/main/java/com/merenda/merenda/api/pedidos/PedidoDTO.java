package com.merenda.merenda.api.pedidos;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PedidoDTO {
    private Long id;
    private Long unidade;
    private Double total;
    private String status;
    private Boolean isaf;
    private String createdAt;
    private String modifiedAt;
    private Boolean isativo;

    private Long totalCart;
    private Long temCart;
    private String nomedaunidade;



    public static PedidoDTO create(Pedido pedido) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pedido, PedidoDTO.class);
    }
}
