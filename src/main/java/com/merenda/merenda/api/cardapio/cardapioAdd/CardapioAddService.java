package com.merenda.merenda.api.cardapio.cardapioAdd;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardapioAddService {

    @Autowired

    private CardapioAddRepository rep;
    public List<CardapioAddDTO> getCardapio() {
        List<CardapioAddDTO> list = rep.findAll().stream().map(CardapioAddDTO::create).collect(Collectors.toList());
        return list;
    }

    public CardapioAddDTO getCardapioById(Long id) {
        Optional<CardapioAdd> carro = rep.findById(id);
        return carro.map(CardapioAddDTO::create).orElseThrow(() -> new ObjectNotFoundException("Cardapio não encontrado"));
    }


    public CardapioAddDTO insert(CardapioAdd cardapioAdd) {
        Assert.isNull(cardapioAdd.getId(),"Não foi possível inserir o registro");
        return CardapioAddDTO.create(rep.save(cardapioAdd));
    }


    public List<CardapioAddDTO> getFornecId(Long id) {
        return rep.findId(id).stream().map(CardapioAddDTO::create).collect(Collectors.toList());
    }

    public CardapioAddDTO update(CardapioAdd cardapioAdd, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<CardapioAdd> optional = rep.findById(id);
        if(optional.isPresent()) {
            CardapioAdd db = optional.get();
            // Copiar as propriedades

            db.setSemana(cardapioAdd.getSemana());
            db.setDia(cardapioAdd.getDia());
            db.setPeriodo(cardapioAdd.getPeriodo());
            db.setIsativo(cardapioAdd.getIsativo());
            db.setModifiedAt(cardapioAdd.getModifiedAt());
            System.out.println("Cardapio id " + db.getId());

            // Atualiza o cardapio
            rep.save(db);

            return CardapioAddDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
