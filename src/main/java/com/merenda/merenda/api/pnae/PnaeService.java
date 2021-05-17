package com.merenda.merenda.api.pnae;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PnaeService {

    @Autowired

    private PnaeRepository rep;
    public List<PnaeDTO> getPnae() {
        List<PnaeDTO> list = rep.findAll().stream().map(PnaeDTO::create).collect(Collectors.toList());
        return list;
    }

    public PnaeDTO getPnaeById(Long id) {
        Optional<Pnae> pnae = rep.findById(id);
        return pnae.map(PnaeDTO::create).orElseThrow(() -> new ObjectNotFoundException("Pnae não encontrado"));
    }


    public double getSoma(Long ano){
        return rep.findSoma(ano);
    }



    public PnaeDTO insert(Pnae pnae) {
        Assert.isNull(pnae.getId(),"Não foi possível inserir o registro");
        return PnaeDTO.create(rep.save(pnae));
    }

    public PnaeDTO update(Pnae pnae, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o pnae no banco de dados
        Optional<Pnae> optional = rep.findById(id);
        if(optional.isPresent()) {
            Pnae db = optional.get();
            // Copiar as propriedades
            db.setAno(pnae.getAno());
            db.setValor(pnae.getValor());
            db.setModifiedAt(pnae.getModifiedAt());
            db.setIsativo(pnae.getIsativo());

            System.out.println("Nivel id " + db.getId());
            rep.save(db);
            return PnaeDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
