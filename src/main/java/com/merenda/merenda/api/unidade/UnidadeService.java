package com.merenda.merenda.api.unidade;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeService {

    @Autowired

    private UnidadeRepository rep;
    public List<UnidadeDTO> getNivel() {
        List<UnidadeDTO> list = rep.findAll().stream().map(UnidadeDTO::create).collect(Collectors.toList());
        return list;
    }

    public UnidadeDTO getNivelById(Long id) {
        Optional<Unidade> escola = rep.findById(id);
        return escola.map(UnidadeDTO::create).orElseThrow(() -> new ObjectNotFoundException("Nivel não encontrado"));
    }


    public List<UnidadeDTO> getEscolasById(Long id) {
        return rep.findEscolaById(id).stream().map(UnidadeDTO::create).collect(Collectors.toList());
    }

    public List<UnidadeDTO> getSetor(Long setor) {
        return rep.findSetor(setor).stream().map(UnidadeDTO::create).collect(Collectors.toList());
    }

    public long getQuantidade(){ return rep.findQuantidade(); }

    public long getQuantAlunos(){ return rep.findQuantAlunos(); }


    //verificado
    public long getQuantAlunosEscola(Long id){ return rep.findQuantAlunosEscola(id); }


    public UnidadeDTO insert(Unidade unidade) {
        Assert.isNull(unidade.getId(),"Não foi possível inserir o registro");
        return UnidadeDTO.create(rep.save(unidade));
    }

    public String getNome(Long id){
        return rep.findNome(id);
    }


    public UnidadeDTO update(Unidade unidade, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o escola no banco de dados
        Optional<Unidade> optional = rep.findById(id);
        if(optional.isPresent()) {
            Unidade db = optional.get();
            // Copiar as propriedades
            db.setNivel(unidade.getNivel());
            db.setNome(unidade.getNome());
            db.setAlias(unidade.getAlias());
            db.setEndereco(unidade.getEndereco());
            db.setBairro(unidade.getBairro());
            db.setAlunos(unidade.getAlunos());
            db.setModifiedAt(unidade.getModifiedAt());
            db.setCreatedAt(unidade.getCreatedAt());
            db.setIsativo(unidade.getIsativo());
            System.out.println("Nivel id " + db.getId());
            // Atualiza o escola
            rep.save(db);

            return UnidadeDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
