package com.merenda.merenda.api.unidadeEscolar;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnidadeEscolarService {

    @Autowired

    private UnidadeEscolarRepository rep;
    public List<UnidadeEscolarDTO> getNivel() {
        List<UnidadeEscolarDTO> list = rep.findAll().stream().map(UnidadeEscolarDTO::create).collect(Collectors.toList());
        return list;
    }

    public UnidadeEscolarDTO getNivelById(Long id) {
        Optional<UnidadeEscolar> carro = rep.findById(id);
        return carro.map(UnidadeEscolarDTO::create).orElseThrow(() -> new ObjectNotFoundException("Nivel não encontrado"));
    }


    public List<UnidadeEscolarDTO> getCarrosById(Long id) {
        return rep.findCarroById(id).stream().map(UnidadeEscolarDTO::create).collect(Collectors.toList());
    }

    public long getQuantidade(){ return rep.findQuantidade(); }

    public long getQuantAlunos(){ return rep.findQuantAlunos(); }

    public long getQuantAlunosNivel(Long nivel){ return rep.findQuantAlunosNivel(nivel); }

    //verificado
    public long getQuantAlunosEscola(Long id){ return rep.findQuantAlunosEscola(id); }

    public long getQuantEscolaNivel(Long nivel){ return rep.findQuantEscolaNivel(nivel); }


    public UnidadeEscolarDTO insert(UnidadeEscolar unidadeEscolar) {
        Assert.isNull(unidadeEscolar.getId(),"Não foi possível inserir o registro");
        return UnidadeEscolarDTO.create(rep.save(unidadeEscolar));
    }

    public String getNome(Long id){
        return rep.findNome(id);
    }


    public UnidadeEscolarDTO update(UnidadeEscolar unidadeEscolar, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<UnidadeEscolar> optional = rep.findById(id);
        if(optional.isPresent()) {
            UnidadeEscolar db = optional.get();
            // Copiar as propriedades
            db.setNivelescolar(unidadeEscolar.getNivelescolar());
            db.setNome(unidadeEscolar.getNome());
            db.setAlias(unidadeEscolar.getAlias());
            db.setEndereco(unidadeEscolar.getEndereco());
            db.setBairro(unidadeEscolar.getBairro());
            db.setAlunos(unidadeEscolar.getAlunos());
            db.setModifiedAt(unidadeEscolar.getModifiedAt());
            db.setCreatedAt(unidadeEscolar.getCreatedAt());
            db.setIsativo(unidadeEscolar.getIsativo());
            System.out.println("Nivel id " + db.getId());
            // Atualiza o carro
            rep.save(db);

            return UnidadeEscolarDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
