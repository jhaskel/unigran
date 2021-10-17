package com.merenda.merenda.api.itens;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItensService {

    @Autowired

    private ItensRepository rep;
    public List<ItensDTO> getItens() {
        List<ItensDTO> list = rep.findAll().stream().map(ItensDTO::create).collect(Collectors.toList());
        return list;
    }




    public ItensDTO getItenById(Long id) {
        Optional<Itens> item = rep.findById(id);
        return item.map(ItensDTO::create).orElseThrow(() -> new ObjectNotFoundException("Iten não encontrado"));
    }

   //verificado
    public List<ItensDTO> getItensByPedido(Long pedido) {
        return rep.findByPedido(pedido).stream().map(ItensDTO::create).collect(Collectors.toList());
    }


    public List<ItensDTO> getItensByAf(Long af) {
        return rep.findByAf(af).stream().map(ItensDTO::create).collect(Collectors.toList());
    }





    //verificado
    public List<ItensDTO> getMaisPedidos( Long ano) {
        return rep.findMaisPedidos(ano).stream().map(ItensDTO::create).collect(Collectors.toList());
    }





//verificado
    public List<ItensDTO> getTotalMes(Long ano) {
        return rep.findTotalMes(ano).stream().map(ItensDTO::create).collect(Collectors.toList());
    }


    //verificado
    public List<ItensDTO> getTotalMesLocal(Long local,Long ano) {
        return rep.findTotalMesLocal(local,ano).stream().map(ItensDTO::create).collect(Collectors.toList());
    }

    //verificado
    public List<ItensDTO> getTotalCategoria(Long ano) {
        return rep.findTotalCategoria(ano).stream().map(ItensDTO::create).collect(Collectors.toList());
    }

    //verificado
    public List<ItensDTO> getTotalCategoriaLocal(Long local,Long ano) {
        return rep.findTotalCategoriaLocal(local,ano).stream().map(ItensDTO::create).collect(Collectors.toList());
    }

    public List<ItensDTO> getTotalLocals(Long ano) {
        return rep.findTotalLocals(ano).stream().map(ItensDTO::create).collect(Collectors.toList());
    }
   //verificado
    public List<ItensDTO> getMediaAlunos(Long ano) {
        return rep.findMediaAlunos(ano).stream().map(ItensDTO::create).collect(Collectors.toList());
    }

    //verificado
    public List<ItensDTO> getProduto(Long produto) {
        return rep.findProduto(produto).stream().map(ItensDTO::create).collect(Collectors.toList());
    }

    //verificado
    public List<ItensDTO> getProduto2(Long produto) {
        return rep.findProduto2(produto).stream().map(ItensDTO::create).collect(Collectors.toList());
    }


    //verificado
    public double getTotal(Long ano){
        return rep.findTotal(ano);
    }
    //verificado
    public double getTradicional(Long ano){
        return rep.findTradicional(ano);
    }
    //verificado
    public double getFamiliar(Long ano){
        return rep.findFamiliar(ano);
    }
    //verificado
    public double getFamiliarLocal(Long local,Long ano){
        return rep.findFamiliarLocal(local,ano);
    }



   //verificado
    public double getTotalLocal(Long local,Long ano){
        return rep.findTotalLocal(local,ano);
    }






    //verificado
    public double getTradicionalLocal(Long local,Long ano){
        return rep.findTradicionalLocal(local,ano);
    }





    public ItensDTO insert(Itens pedidoItens) {
        Assert.isNull(pedidoItens.getId(),"Não foi possível inserir o registro");
        return ItensDTO.create(rep.save(pedidoItens));
    }

    public ItensDTO update(Itens itens, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o item no banco de dados
        Optional<Itens> optional = rep.findById(id);
        if(optional.isPresent()) {
            Itens db = optional.get();
            // Copiar as propriedades
            db.setLocal(itens.getLocal());
            db.setProduto(itens.getProduto());
            db.setCategoria(itens.getCategoria());

            db.setFornecedor(itens.getFornecedor());
            db.setAno(itens.getAno());
            db.setAf(itens.getAf());
            db.setPedido(itens.getPedido());
            db.setAlias(itens.getAlias());

            db.setStatus(itens.getStatus());
            db.setMes(itens.getModifiedAt());

            db.setUnidade(itens.getUnidade());
            db.setQuantidade(itens.getQuantidade());
            db.setValor(itens.getValor());
            db.setTotal(itens.getTotal());

            db.setMes(itens.getMes());
            db.setIsativo(itens.getIsativo());

            System.out.println("Iten id " + db.getId());

            // Atualiza o item
            rep.save(db);

            return ItensDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);
    }


}
