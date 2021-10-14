package com.merenda.merenda.api.nivel;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import com.merenda.merenda.api.itens.ItensDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NivelService {

    @Autowired

    private NivelRepository rep;
    public List<NivelDTO> getNivel() {
        List<NivelDTO> list = rep.findAll().stream().map(NivelDTO::create).collect(Collectors.toList());
        return list;
    }

    public NivelDTO getNivelById(Long id) {
        Optional<Nivel> nivel = rep.findById(id);
        return nivel.map(NivelDTO::create).orElseThrow(() -> new ObjectNotFoundException("Nivel não encontrado"));
    }

    public String getRe(Long id){
        return rep.findNome(id);
    }

    public List<NivelDTO> getId(Long id) {
        return rep.findId(id).stream().map(NivelDTO::create).collect(Collectors.toList());
    }

    //verificado
    public List<NivelDTO> getSetor(Long setor) {
        return rep.findSetor(setor).stream().map(NivelDTO::create).collect(Collectors.toList());
    }


    public NivelDTO insert(Nivel nivel) {
        Assert.isNull(nivel.getId(),"Não foi possível inserir o registro");
        return NivelDTO.create(rep.save(nivel));
    }

    public NivelDTO update(Nivel nivel, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o nivel no banco de dados
        Optional<Nivel> optional = rep.findById(id);
        if(optional.isPresent()) {
            Nivel db = optional.get();
            // Copiar as propriedades
            db.setNome(nivel.getNome());
            db.setIsativo(nivel.getIsativo());
            db.setModifiedAt(nivel.getModifiedAt());
            db.setSetor(nivel.getSetor());
            db.setModifiedBy(nivel.getModifiedBy());
            System.out.println("Nivel id " + db.getId());
            rep.save(db);
            return NivelDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
