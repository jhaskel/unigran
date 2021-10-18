package com.merenda.merenda.api.licitacao;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LicitacaoService {

    @Autowired

    private LicitacaoRepository rep;
    public List<LicitacaoDTO> getCart() {
        List<LicitacaoDTO> list = rep.findAll().stream().map(LicitacaoDTO::create).collect(Collectors.toList());
        return list;
    }

    public LicitacaoDTO getCartById(Long id) {
        Optional<Licitacao> cart = rep.findById(id);
        return cart.map(LicitacaoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Cart não encontrado"));
    }

    public List<LicitacaoDTO> getProcesso(String processo) {
        return rep.findProcesso(processo).stream().map(LicitacaoDTO::create).collect(Collectors.toList());
    }





    public LicitacaoDTO insert(Licitacao licitacao) {
        Assert.isNull(licitacao.getId(),"Não foi possível inserir o registro");
        return LicitacaoDTO.create(rep.save(licitacao));
    }

    public LicitacaoDTO update(Licitacao licitacao, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o cart no banco de dados
        Optional<Licitacao> optional = rep.findById(id);
        if(optional.isPresent()) {
            Licitacao db = optional.get();
            // Copiar as propriedades
           /*
            db.setEscola(cart.getEscola());
            db.setProduto(cart.getProduto());
            db.setCategoria(cart.getCategoria());
            db.setFornecedor(cart.getFornecedor());
            db.setUnidade(cart.getUnidade());
            db.setCod(cart.getCod());
            db.setProcesso(cart.getProcesso());
            */

            /*db.setValor(cart.getValor());*/

            /*db.setObs(cart.getObs());*/
            //db.setCreatedAt(cart.getCreatedAt());
            System.out.println("Cart id " + db.getId());

            // Atualiza o cart
            rep.save(db);

            return LicitacaoDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
