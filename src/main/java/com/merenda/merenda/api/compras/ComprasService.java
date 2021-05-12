package com.merenda.merenda.api.compras;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComprasService {

    @Autowired

    private ComprasRepository rep;
    public List<ComprasDTO> getCompras() {
        List<ComprasDTO> list = rep.findAll().stream().map(ComprasDTO::create).collect(Collectors.toList());
        return list;
    }
    public List<ComprasDTO> getCarrosByPedido(String pedido) {
        return rep.findByPedido(pedido).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }



    public ComprasDTO insert(Compras compras) {
        Assert.isNull(compras.getId(),"Não foi possível inserir o registro");
        return ComprasDTO.create(rep.save(compras));
    }



    public ComprasDTO update(Compras compras, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Compras> optional = rep.findById(id);
        if(optional.isPresent()) {
            Compras db = optional.get();
            // Copiar as propriedades
            db.setEscola(compras.getEscola());
            db.setProduto(compras.getProduto());
            db.setCategoria(compras.getCategoria());

            db.setFornecedor(compras.getFornecedor());
            db.setAno(compras.getAno());
            db.setAf(compras.getAf());
            db.setPedido(compras.getPedido());
            db.setAlias(compras.getAlias());

            db.setStatus(compras.getStatus());
            db.setModifiedAt(compras.getModifiedAt());

            db.setUnidade(compras.getUnidade());
            db.setQuantidade(compras.getQuantidade());
            db.setValor(compras.getValor());
            db.setTotal(compras.getTotal());

            db.setMes(compras.getMes());
            db.setIsativo(compras.getIsativo());

            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return ComprasDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);
    }


}
