package com.merenda.merenda.api.pedidos;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired

    private PedidoRepository rep;
    public List<PedidoDTO> getPedido() {
        List<PedidoDTO> list = rep.findAll().stream().map(PedidoDTO::create).collect(Collectors.toList());
        return list;
    }

    public PedidoDTO getPedidoById(Long id) {
        Optional<Pedido> pedido = rep.findById(id);
        return pedido.map(PedidoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }



    public List<PedidoDTO> getPedidoByEscola(Long escola) {
        return rep.findByEscola(escola).stream().map(PedidoDTO::create).collect(Collectors.toList());
    }



    public long getPedidoSemAf(){
        return rep.findPedidoSemAf();
    }


    public List<PedidoDTO> getId(Long id) {
        return rep.findId(id).stream().map(PedidoDTO::create).collect(Collectors.toList());
    }




    public PedidoDTO insert(Pedido pedido) {
        Assert.isNull(pedido.getId(),"Não foi possível inserir o registro");
        return PedidoDTO.create(rep.save(pedido));
    }

    public PedidoDTO update(Pedido pedido, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o pedido no banco de dados
        Optional<Pedido> optional = rep.findById(id);
        if(optional.isPresent()) {
            Pedido db = optional.get();
            // Copiar as propriedades
            db.setIsativo(pedido.getIsativo());
            db.setModifiedAt(pedido.getModifiedAt());
            db.setStatus(pedido.getStatus());
            db.setIsaf(pedido.getIsaf());
            db.setTotal(pedido.getTotal());
            System.out.println("Pedido id " + db.getId());

            // Atualiza o pedido
            rep.save(db);

            return PedidoDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
