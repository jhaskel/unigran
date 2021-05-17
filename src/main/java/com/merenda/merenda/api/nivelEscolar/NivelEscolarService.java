package com.merenda.merenda.api.nivelEscolar;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NivelEscolarService {

    @Autowired

    private NivelEscolarRepository rep;
    public List<NivelEscolarDTO> getNivel() {
        List<NivelEscolarDTO> list = rep.findAll().stream().map(NivelEscolarDTO::create).collect(Collectors.toList());
        return list;
    }

    public NivelEscolarDTO getNivelById(Long id) {
        Optional<NivelEscolar> nivel = rep.findById(id);
        return nivel.map(NivelEscolarDTO::create).orElseThrow(() -> new ObjectNotFoundException("Nivel não encontrado"));
    }

    public String getRe(Long id){
        return rep.findNome(id);
    }

    public List<NivelEscolarDTO> getId(Long id) {
        return rep.findId(id).stream().map(NivelEscolarDTO::create).collect(Collectors.toList());
    }


    public NivelEscolarDTO insert(NivelEscolar nivelEscolar) {
        Assert.isNull(nivelEscolar.getId(),"Não foi possível inserir o registro");
        return NivelEscolarDTO.create(rep.save(nivelEscolar));
    }

    public NivelEscolarDTO update(NivelEscolar nivelEscolar, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o nivel no banco de dados
        Optional<NivelEscolar> optional = rep.findById(id);
        if(optional.isPresent()) {
            NivelEscolar db = optional.get();
            // Copiar as propriedades
            db.setNome(nivelEscolar.getNome());
            db.setIsativo(nivelEscolar.getIsativo());
            db.setModifiedAt(nivelEscolar.getModifiedAt());
            System.out.println("Nivel id " + db.getId());
            rep.save(db);
            return NivelEscolarDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
