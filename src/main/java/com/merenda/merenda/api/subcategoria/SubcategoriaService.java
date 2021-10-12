package com.merenda.merenda.api.subcategoria;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubcategoriaService {

    @Autowired

    private SubcategoriaRepository rep;
    public List<SubcategoriaDTO> getCategorias() {
        List<SubcategoriaDTO> list = rep.findAll().stream().map(SubcategoriaDTO::create).collect(Collectors.toList());
        return list;
    }


    public SubcategoriaDTO getCategoriaById(Long id) {
        Optional<Subcategoria> carro = rep.findById(id);
        return carro.map(SubcategoriaDTO::create).orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrado"));
    }





    public List<SubcategoriaDTO> getCategoriaId(Long id) {
        return rep.findId(id).stream().map(SubcategoriaDTO::create).collect(Collectors.toList());
    }



    public SubcategoriaDTO insert(Subcategoria subcategoria) {
        Assert.isNull(subcategoria.getId(),"Não foi possível inserir o registro");
        return SubcategoriaDTO.create(rep.save(subcategoria));
    }

    public SubcategoriaDTO update(Subcategoria subcategoria, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Subcategoria> optional = rep.findById(id);
        if(optional.isPresent()) {
            Subcategoria db = optional.get();
            // Copiar as propriedades
            db.setNome(subcategoria.getNome());

            db.setIsativo(subcategoria.getIsativo());
            db.setModifiedAt(subcategoria.getModifiedAt());
            System.out.println("Categoria id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return SubcategoriaDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
