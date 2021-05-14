package com.merenda.merenda.api.af;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AfService {

    @Autowired

    private AfRepository rep;
    public List<AfDTO> getCarros() {
        List<AfDTO> list = rep.findAll().stream().map(AfDTO::create).collect(Collectors.toList());
        return list;
    }



    public AfDTO getCarroById(Long id) {
        Optional<Af> carro = rep.findById(id);
        return carro.map(AfDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }


    public List<AfDTO> getByFornecedor(Long fornecedor) {
        return rep.findByFornecedor(fornecedor).stream().map(AfDTO::create).collect(Collectors.toList());
    }

    public List<AfDTO> getByAf(Long af) {
        return rep.findByAf(af).stream().map(AfDTO::create).collect(Collectors.toList());
    }




    public long getAfEnviada(){
        return rep.findAfEnviada();
    }


    public AfDTO insert(Af af) {
        Assert.isNull(af.getId(),"Não foi possível inserir o registro");
        return AfDTO.create(rep.save(af));
    }

    public AfDTO update(Af af, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Af> optional = rep.findById(id);
        if(optional.isPresent()) {
            Af db = optional.get();
            // Copiar as propriedades
            db.setCode(af.getCode());
            db.setFornecedor(af.getFornecedor());
            db.setIsenviado(af.getIsenviado());
            db.setStatus(af.getStatus());
            db.setIsativo(af.getIsativo());

            System.out.println("Af id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return AfDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);
    }


}
