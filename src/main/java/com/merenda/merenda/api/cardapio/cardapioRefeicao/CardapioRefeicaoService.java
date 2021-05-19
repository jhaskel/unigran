package com.merenda.merenda.api.cardapio.cardapioRefeicao;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardapioRefeicaoService {

    @Autowired

    private CardapioRefeicaoRepository rep;
    public List<CardapioRefeicaoDTO> getCardapio() {
        List<CardapioRefeicaoDTO> list = rep.findAll().stream().map(CardapioRefeicaoDTO::create).collect(Collectors.toList());
        return list;
    }

    public CardapioRefeicaoDTO getCardapioById(Long id) {
        Optional<CardapioRefeicao> carro = rep.findById(id);
        return carro.map(CardapioRefeicaoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Cardapio não encontrado"));
    }


    public CardapioRefeicaoDTO insert(CardapioRefeicao cardapioRefeicao) {
        Assert.isNull(cardapioRefeicao.getId(),"Não foi possível inserir o registro");
        return CardapioRefeicaoDTO.create(rep.save(cardapioRefeicao));
    }


    public List<CardapioRefeicaoDTO> getFornecId(Long id) {
        return rep.findId(id).stream().map(CardapioRefeicaoDTO::create).collect(Collectors.toList());
    }

    public CardapioRefeicaoDTO update(CardapioRefeicao cardapioRefeicao, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<CardapioRefeicao> optional = rep.findById(id);
        if(optional.isPresent()) {
            CardapioRefeicao db = optional.get();
            // Copiar as propriedades

            db.setCardapio(cardapioRefeicao.getCardapio());
            db.setRefeicao(cardapioRefeicao.getRefeicao());

            System.out.println("Cardapio id " + db.getId());

            // Atualiza o cardapio
            rep.save(db);

            return CardapioRefeicaoDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
