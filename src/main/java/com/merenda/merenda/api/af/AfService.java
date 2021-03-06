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

    public List<AfDTO> getAf() {
        List<AfDTO> list = rep.findAll().stream().map(AfDTO::create).collect(Collectors.toList());
        return list;
    }
    public List<AfDTO> getAll() {
        List<AfDTO> list = rep.findAll2().stream().map(AfDTO::create).collect(Collectors.toList());
        return list;
    }


    public AfDTO getAfById(Long id) {
        Optional<Af> carro = rep.findById(id);
        return carro.map(AfDTO::create).orElseThrow(() -> new ObjectNotFoundException("Af não encontrado"));
    }


    public List<AfDTO> getByFornecedor(Long fornecedor) {
        return rep.findByFornecedor(fornecedor).stream().map(AfDTO::create).collect(Collectors.toList());
    }


    public List<AfDTO> getSetor(Long setor) {
        return rep.findSetor(setor).stream().map(AfDTO::create).collect(Collectors.toList());
    }


    public List<AfDTO> getByFornecedorTest(Long fornecedor) {
        return rep.findByFornecedorTest(fornecedor).stream().map(AfDTO::create).collect(Collectors.toList());
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
