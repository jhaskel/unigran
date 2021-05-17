package com.merenda.merenda.api.pedidos.pedidoAdd;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoAddService {

    @Autowired

    private PedidoAddRepository rep;
    public List<PedidoAddDTO> getPedido() {
        List<PedidoAddDTO> list = rep.findAll().stream().map(PedidoAddDTO::create).collect(Collectors.toList());
        return list;
    }

    public PedidoAddDTO getPedidoById(Long id) {
        Optional<PedidoAdd> pedido = rep.findById(id);
        return pedido.map(PedidoAddDTO::create).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }


    public PedidoAddDTO insert(PedidoAdd pedido) {
        Assert.isNull(pedido.getId(),"Não foi possível inserir o registro");
        return PedidoAddDTO.create(rep.save(pedido));
    }

    public PedidoAddDTO update(PedidoAdd pedido, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o pedido no banco de dados
        Optional<PedidoAdd> optional = rep.findById(id);
        if(optional.isPresent()) {
            PedidoAdd db = optional.get();
            // Copiar as propriedades
            db.setIsativo(pedido.getIsativo());
            db.setModifiedAt(pedido.getModifiedAt());
            db.setStatus(pedido.getStatus());
            db.setIsaf(pedido.getIsaf());
            db.setTotal(pedido.getTotal());
            System.out.println("Pedido id " + db.getId());

            // Atualiza o pedido
            rep.save(db);

            return PedidoAddDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
