package com.merenda.merenda.api.refeicao;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RefeicaoService {

    @Autowired

    private RefeicaoRepository rep;
    public List<RefeicaoDTO> getRefeicao() {
        List<RefeicaoDTO> list = rep.findAll().stream().map(RefeicaoDTO::create).collect(Collectors.toList());
        return list;
    }

    public RefeicaoDTO getRefeicaoById(Long id) {
        Optional<Refeicao> carro = rep.findById(id);
        return carro.map(RefeicaoDTO::create).orElseThrow(() -> new ObjectNotFoundException("Refeicao não encontrado"));
    }


    public RefeicaoDTO insert(Refeicao refeicao) {
        Assert.isNull(refeicao.getId(),"Não foi possível inserir o registro");
        return RefeicaoDTO.create(rep.save(refeicao));
    }


    public List<RefeicaoDTO> getFornecId(Long id) {
        return rep.findId(id).stream().map(RefeicaoDTO::create).collect(Collectors.toList());
    }

    public RefeicaoDTO update(Refeicao refeicao, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Refeicao> optional = rep.findById(id);
        if(optional.isPresent()) {
            Refeicao db = optional.get();
            // Copiar as propriedades
            db.setNome(refeicao.getNome());
            db.setImagem(refeicao.getImagem());
            db.setKcal(refeicao.getKcal());
            db.setIsativo(refeicao.getIsativo());
            db.setModifiedAt(refeicao.getModifiedAt());          

            // Atualiza o refeicao
            rep.save(db);

            return RefeicaoDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
