package com.merenda.merenda.api.compras;

import com.merenda.merenda.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComprasService {

    @Autowired

    private ComprasRepository rep;
    public List<ComprasDTO> getCarros() {
        List<ComprasDTO> list = rep.findAll().stream().map(ComprasDTO::create).collect(Collectors.toList());
        return list;
    }

    public List<ComprasDTO> getCarros2() {
        List<ComprasDTO> list = rep.findAll2().stream().map(ComprasDTO::create).collect(Collectors.toList());
        return list;
    }

    public List<ComprasDTO> getCarros3() {
        List<ComprasDTO> list = rep.findAll3().stream().map(ComprasDTO::create).collect(Collectors.toList());
        return list;
    }

    public ComprasDTO getCarroById(Long id) {
        Optional<Compras> carro = rep.findById(id);
        return carro.map(ComprasDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
    }


    public List<ComprasDTO> getCarrosByPedido(String pedido) {
        return rep.findByPedido(pedido).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getCarrosByPedidoAll(String pedido) {
        return rep.findByPedidoAll(pedido).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getCarrosByAf(Long af) {
        return rep.findByAf(af).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getCarrosByEscola(Long escola, Long pedido) {
        return rep.findByEscola(escola,pedido).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getEscolar(Long escola, Long ano) {
        return rep.findEscolar(escola,ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getByFornecedor(Long fornecedor) {
        return rep.findByFornecedor(fornecedor).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getEscolaAll(Long ano) {
        return rep.findEscolaAll(ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getItensAno(Long ano) {
        return rep.findItensAno(ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }



    public List<ComprasDTO> getTotalMes(Long ano) {
        return rep.findTotalMes(ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getTotalMesNivel(Long nivel, Long ano) {
        return rep.findTotalMesNivel(nivel,ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getTotalMesEscola(Long escola, Long ano) {
        return rep.findTotalMesEscola(escola,ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getTotalCategoria(Long ano) {
        return rep.findTotalCategoria(ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getTotalCategoriaNivel(Long nivel, Long ano) {
        return rep.findTotalCategoriaNivel(nivel,ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getTotalCategoriaEscola(Long escola, Long ano) {
        return rep.findTotalCategoriaEscola(escola,ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }

    public List<ComprasDTO> getTotalEscolas(Long ano) {
        return rep.findTotalEscolas(ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getTotalEscolaNivel(Long nivel, Long ano) {
        return rep.findTotalEscolaNivel(nivel,ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }




    public List<ComprasDTO> getMediaAlunos(Long ano) {
        return rep.findMediaAlunos(ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public List<ComprasDTO> getMediaAlunosNivel(Long nivel, Long ano) {
        return rep.findMediaAlunosNivel(nivel,ano).stream().map(ComprasDTO::create).collect(Collectors.toList());
    }


    public double getTotal(Long ano){
        return rep.findTotal(ano);
    }


    public double getTotalNivel(Long nivel,Long ano){
        return rep.findTotalNivel(nivel,ano);
    }


    public double getTotalEscola(Long escola,Long ano){
        return rep.findTotalEscola(escola,ano);
    }


   public double getSoma(Long escola){
        return rep.findSoma(escola);
    }



    public double getTotalAgroEscola(Long escola,Long ano){
        return rep.findTotalAgroEscola(escola,ano);
    }

    public double getTotalAgroNivel(Long nivel,Long ano){
        return rep.findTotalAgroNivel(nivel,ano);
    }


    public double getTotalAgro(Long ano){
        return rep.findTotalAgro(ano);
    }

    public double getTotalPedido(String pedido){
        return rep.findTotalPedido(pedido);
    }

    public double getTotalAf(Long af){
        return rep.findTotalAf(af);
    }

    public double getTradicional(Long ano){
        return rep.findTradicional(ano);
    }


    public double getTradicionalNivel(Long nivel,Long ano){
        return rep.findTradicionalNivel(nivel,ano);
    }


    public double getTradicionalEscola(Long escola,Long ano){
        return rep.findTradicionalEscola(escola,ano);
    }



    public double getDiversos(Long ano){
        return rep.findDiversos(ano);
    }


    public double getDiversosNivel(Long nivel,Long ano){
        return rep.findDiversosNivel(nivel,ano);
    }


    public double getDiversosEscola(Long escola,Long ano){
        return rep.findDiversosEscola(escola,ano);
    }



    public ComprasDTO insert(Compras compras) {
        Assert.isNull(compras.getId(),"Não foi possível inserir o registro");
        return ComprasDTO.create(rep.save(compras));
    }



    public ComprasDTO update(Compras compras, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Compras> optional = rep.findById(id);
        if(optional.isPresent()) {
            Compras db = optional.get();
            // Copiar as propriedades
            db.setEscola(compras.getEscola());
            db.setProduto(compras.getProduto());
            db.setCategoria(compras.getCategoria());
            db.setNivel(compras.getNivel());
            db.setFornecedor(compras.getFornecedor());
            db.setAno(compras.getAno());
            db.setAf(compras.getAf());
            db.setPedido(compras.getPedido());
            db.setAlias(compras.getAlias());
            db.setObs(compras.getObs());
            db.setStatus(compras.getStatus());
            db.setModifiedAt(compras.getModifiedAt());
            db.setNomeescola(compras.getNomeescola());
            db.setNomenivel(compras.getNomenivel());
            db.setUnidade(compras.getUnidade());
            db.setQuantidade(compras.getQuantidade());
            db.setValor(compras.getValor());
            db.setTotal(compras.getTotal());
            db.setIsagro(compras.getIsagro());
            db.setIsautorizado(compras.getIsautorizado());
            db.setIscheck(compras.getIscheck());
            db.setCod(compras.getCod());
            db.setMes(compras.getMes());
            db.setIsativo(compras.getIsativo());

            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return ComprasDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }


    public void delete(Long id) {
        rep.deleteById(id);
    }


}
