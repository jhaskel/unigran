package com.merenda.merenda.api.carros;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import com.merenda.merenda.api.nivelEscolar.NivelEscolar;
import com.merenda.merenda.api.nivelEscolar.NivelEscolarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;
    public List<CarroDTO> getCarros() {
        List<CarroDTO> list = rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
        return list;
    }

    public CarroDTO getCarroById(Long id) {
        Optional<Carro> carro = rep.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(),"Não foi possível inserir o registro");
        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro) {
        Long id = carro.getId();
        // Busca o carro no banco de dados
        Optional<Carro> optional = rep.findById(id);
        if(optional.isPresent()) {
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            db.setDescricao(carro.getDescricao());
            rep.save(db);
            return CarroDTO.create(db);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
