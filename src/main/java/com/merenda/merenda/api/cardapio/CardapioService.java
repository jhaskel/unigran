package com.merenda.merenda.api.cardapio;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardapioService {

    @Autowired

    private CardapioRepository rep;
    public List<CardapioDTO> getCardapio() {
        List<CardapioDTO> list = rep.findAll().stream().map(CardapioDTO::create).collect(Collectors.toList());
        return list;
    }

    public CardapioDTO getCardapioById(Long id) {
        Optional<Cardapio> carro = rep.findById(id);
        return carro.map(CardapioDTO::create).orElseThrow(() -> new ObjectNotFoundException("Cardapio não encontrado"));
    }


    public CardapioDTO insert(Cardapio cardapio) {
        Assert.isNull(cardapio.getId(),"Não foi possível inserir o registro");
        return CardapioDTO.create(rep.save(cardapio));
    }


    public List<CardapioDTO> getFornecId(Long id) {
        return rep.findId(id).stream().map(CardapioDTO::create).collect(Collectors.toList());
    }

    public CardapioDTO update(Cardapio cardapio, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Cardapio> optional = rep.findById(id);
        if(optional.isPresent()) {
            Cardapio db = optional.get();
            // Copiar as propriedades


            db.setEscola(cardapio.getEscola());
            db.setNomedaescola(cardapio.getNomedaescola());
            db.setTitle(cardapio.getTitle());
            db.setImagem(cardapio.getImagem());
            db.setIsativo(cardapio.getIsativo());
            db.setModifiedAt(cardapio.getModifiedAt());
            System.out.println("Cardapio id " + db.getId());

            // Atualiza o cardapio
            rep.save(db);

            return CardapioDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
