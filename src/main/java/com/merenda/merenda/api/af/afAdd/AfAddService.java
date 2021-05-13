package com.merenda.merenda.api.af.afAdd;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AfAddService {

    @Autowired

    private AfAddRepository rep;
    public List<AfAddDTO> getCarros() {
        List<AfAddDTO> list = rep.findAll().stream().map(AfAddDTO::create).collect(Collectors.toList());
        return list;
    }


    public AfAddDTO getCarroById(Long id) {
        Optional<AfAdd> carro = rep.findById(id);
        return carro.map(AfAddDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }



    public AfAddDTO insert(AfAdd afAdd) {
        Assert.isNull(afAdd.getId(),"Não foi possível inserir o registro");
        return AfAddDTO.create(rep.save(afAdd));
    }

    public AfAddDTO update(AfAdd afAdd, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<AfAdd> optional = rep.findById(id);
        if(optional.isPresent()) {
            AfAdd db = optional.get();
            // Copiar as propriedades
            db.setCode(afAdd.getCode());
            db.setFornecedor(afAdd.getFornecedor());
            db.setIsenviado(afAdd.getIsenviado());
            db.setStatus(afAdd.getStatus());
            db.setIsativo(afAdd.getIsativo());

            System.out.println("Af id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return AfAddDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);
    }


}
