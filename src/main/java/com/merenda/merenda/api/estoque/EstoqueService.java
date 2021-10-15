package com.merenda.merenda.api.estoque;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired

    private EstoqueRepository rep;
    public List<EstoqueDTO> getProdutos() {
        List<EstoqueDTO> list = rep.findAll().stream().map(EstoqueDTO::create).collect(Collectors.toList());
        return list;
    }

    public EstoqueDTO getProdutoById(Long id) {
        Optional<Estoque> produto = rep.findById(id);
        return produto.map(EstoqueDTO::create).orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado"));
    }


    public List<EstoqueDTO> getProdutosByEscola(Long escola) {
        return rep.findByEcola(escola).stream().map(EstoqueDTO::create).collect(Collectors.toList());
    }



    public List<EstoqueDTO> getEstoqueByUnidade(Long setor) {
        return rep.findEstoqueByUnidade(setor).stream().map(EstoqueDTO::create).collect(Collectors.toList());
    }


    public List<EstoqueDTO> getId(Long id) {
        return rep.findId(id).stream().map(EstoqueDTO::create).collect(Collectors.toList());
    }


    public List<EstoqueDTO> getMenos() {
        return rep.findMenos().stream().map(EstoqueDTO::create).collect(Collectors.toList());
    }

    public EstoqueDTO insert(Estoque estoque) {
        Assert.isNull(estoque.getId(),"Não foi possível inserir o registro");
        return EstoqueDTO.create(rep.save(estoque));
    }

    public EstoqueDTO update(Estoque estoque, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o produto no banco de dados
        Optional<Estoque> optional = rep.findById(id);
        if(optional.isPresent()) {
            Estoque db = optional.get();
            // Copiar as propriedades
            db.setCode(estoque.getCode());
            db.setAlias(estoque.getAlias());
            db.setAlias(estoque.getAlias());
            db.setUnidade(estoque.getUnidade());
            db.setCategoria(estoque.getCategoria());
            db.setFornecedor(estoque.getFornecedor());
            db.setImage(estoque.getImage());
            db.setQuantidade(estoque.getQuantidade());
            db.setAgrofamiliar(estoque.getAgrofamiliar());
            db.setAno(estoque.getAno());
            db.setModifiedAt(estoque.getModifiedAt());

            db.setIsativo(estoque.getIsativo());
            db.setValor(estoque.getValor());

            System.out.println("Produto id " + db.getId());

            // Atualiza o produto
            rep.save(db);

            return EstoqueDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }


}
