package com.merenda.merenda.api.fornecedor;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    @Autowired

    private FornecedorRepository rep;
    public List<FornecedorDTO> getCarros() {
        List<FornecedorDTO> list = rep.findAll().stream().map(FornecedorDTO::create).collect(Collectors.toList());
        return list;
    }

    public FornecedorDTO getCarroById(Long id) {
        Optional<Fornecedor> carro = rep.findById(id);
        return carro.map(FornecedorDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }


    public FornecedorDTO insert(Fornecedor fornecedor) {
        Assert.isNull(fornecedor.getId(),"Não foi possível inserir o registro");
        return FornecedorDTO.create(rep.save(fornecedor));
    }


    public List<FornecedorDTO> getFornecId(Long id) {
        return rep.findId(id).stream().map(FornecedorDTO::create).collect(Collectors.toList());
    }

    public FornecedorDTO update(Fornecedor fornecedor, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Fornecedor> optional = rep.findById(id);
        if(optional.isPresent()) {
            Fornecedor db = optional.get();
            // Copiar as propriedades
            db.setNome(fornecedor.getNome());
            db.setCnpj(fornecedor.getCnpj());
            db.setAlias(fornecedor.getAlias());
            db.setEmail(fornecedor.getEmail());
            db.setCelular(fornecedor.getCelular());
            db.setIsativo(fornecedor.getIsativo());
            db.setModifiedAt(fornecedor.getModifiedAt());
            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return FornecedorDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
