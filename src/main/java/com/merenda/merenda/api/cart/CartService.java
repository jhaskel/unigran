package com.merenda.merenda.api.cart;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired

    private CartRepository rep;
    public List<CartDTO> getCarros() {
        List<CartDTO> list = rep.findAll().stream().map(CartDTO::create).collect(Collectors.toList());
        return list;
    }

    public CartDTO getCarroById(Long id) {
        Optional<Cart> carro = rep.findById(id);
        return carro.map(CartDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public List<CartDTO> getCarrosByEscola(Long escola) {
        return rep.findByEcola(escola).stream().map(CartDTO::create).collect(Collectors.toList());
    }


    public double getSoma(Long escola){
        return rep.findSoma(escola);
    }




    public CartDTO insert(Cart cart) {
        Assert.isNull(cart.getId(),"Não foi possível inserir o registro");
        return CartDTO.create(rep.save(cart));
    }

    public CartDTO update(Cart cart, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Cart> optional = rep.findById(id);
        if(optional.isPresent()) {
            Cart db = optional.get();
            // Copiar as propriedades
            db.setEscola(cart.getEscola());
            db.setProduto(cart.getProduto());
            db.setCategoria(cart.getCategoria());
            db.setFornecedor(cart.getFornecedor());
            db.setUnidade(cart.getUnidade());
            db.setCod(cart.getCod());
            db.setProcesso(cart.getProcesso());
            db.setQuantidade(cart.getQuantidade());
            db.setValor(cart.getValor());
            db.setTotal(cart.getTotal());
            db.setObs(cart.getObs());
            db.setCreatedAt(cart.getCreatedAt());
            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return CartDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}