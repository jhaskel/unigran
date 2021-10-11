package com.merenda.merenda.api.setor;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired

    private SetorRepository rep;
    public List<SetorDTO> getCategorias() {
        List<SetorDTO> list = rep.findAll().stream().map(SetorDTO::create).collect(Collectors.toList());
        return list;
    }


    public SetorDTO getCategoriaById(Long id) {
        Optional<Setor> carro = rep.findById(id);
        return carro.map(SetorDTO::create).orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrado"));
    }


    public List<SetorDTO> getCategoriaId(Long id) {
        return rep.findId(id).stream().map(SetorDTO::create).collect(Collectors.toList());
    }



    public SetorDTO insert(Setor setor) {
        Assert.isNull(setor.getId(),"Não foi possível inserir o registro");
        return SetorDTO.create(rep.save(setor));
    }

    public SetorDTO update(Setor setor, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Setor> optional = rep.findById(id);
        if(optional.isPresent()) {
            Setor db = optional.get();
            // Copiar as propriedades
            db.setNome(setor.getNome());

            db.setIsativo(setor.getIsativo());
            db.setModifiedAt(setor.getModifiedAt());
            System.out.println("Categoria id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return SetorDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
