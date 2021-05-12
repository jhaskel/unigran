package com.merenda.merenda.api.afPedido;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AfPedidoService {

    @Autowired

    private AfPedidoRepository rep;
    public List<AfPedidoDTO> getCarros() {
        List<AfPedidoDTO> list = rep.findAll().stream().map(AfPedidoDTO::create).collect(Collectors.toList());
        return list;
    }



    public AfPedidoDTO getCarroById(Long id) {
        Optional<AfPedido> carro = rep.findById(id);
        return carro.map(AfPedidoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }



    public AfPedidoDTO insert(AfPedido afPedido) {
        Assert.isNull(afPedido.getId(),"Não foi possível inserir o registro");
        return AfPedidoDTO.create(rep.save(afPedido));
    }

    public AfPedidoDTO update(AfPedido afPedido, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<AfPedido> optional = rep.findById(id);
        if(optional.isPresent()) {
            AfPedido db = optional.get();
            // Copiar as propriedades
            db.setAf(afPedido.getAf());
            db.setPedido(afPedido.getPedido());
            db.setTotal(afPedido.getTotal());
            db.setFornecedor(afPedido.getFornecedor());



            System.out.println("Af id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return AfPedidoDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);
    }


}
