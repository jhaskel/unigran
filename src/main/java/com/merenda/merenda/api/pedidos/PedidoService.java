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
    public List<PedidoDTO> getCarros() {
        List<PedidoDTO> list = rep.findAll().stream().map(PedidoDTO::create).collect(Collectors.toList());
        return list;
    }

    public PedidoDTO getCarroById(Long id) {
        Optional<Pedido> carro = rep.findById(id);
        return carro.map(PedidoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }



    public List<PedidoDTO> getCarrosByCode(String code) {
        return rep.findByCode(code).stream().map(PedidoDTO::create).collect(Collectors.toList());
    }


    public List<PedidoDTO> getCarrosByEscola(Long escola) {
        return rep.findByEscola(escola).stream().map(PedidoDTO::create).collect(Collectors.toList());
    }

    public long getPedidoSemAf(){
        return rep.findPedidoSemAf();
    }

    public long getUltimoId(){
        return rep.findUltimoId();
    }

    public long getTemCart(Long escola){
        return rep.findTemCart(escola);
    }

    public long getTemCart1(Long escola){
        return rep.findTemCart1(escola);
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

        // Busca o carro no banco de dados
        Optional<Pedido> optional = rep.findById(id);
        if(optional.isPresent()) {
            Pedido db = optional.get();
            // Copiar as propriedades
            db.setIsativo(pedido.getIsativo());
            db.setModifiedAt(pedido.getModifiedAt());
            db.setStatus(pedido.getStatus());
            db.setIsaf(pedido.getIsaf());
            db.setTotal(pedido.getTotal());
            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
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
