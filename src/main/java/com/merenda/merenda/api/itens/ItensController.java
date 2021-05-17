package com.merenda.merenda.api.itens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/itens")
public class ItensController {
    @Autowired
    private ItensService service;


    @GetMapping()
    public ResponseEntity get() {
        List<ItensDTO> itens = service.getItens();
        return ResponseEntity.ok(itens);
    }
    @GetMapping("/afi")
    public ResponseEntity get2() {
        List<ItensDTO> itens = service.getItens2();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/pedidos")
    public ResponseEntity get3() {
        List<ItensDTO> itens = service.getItens3();
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        ItensDTO iten = service.getItenById(id);

        return ResponseEntity.ok(iten);
    }



   //verificado
    @GetMapping("/pedido/{pedido}")
    public ResponseEntity getItensByPedido(@PathVariable("pedido") Long pedido) {
        List<ItensDTO> itens = service.getItensByPedido(pedido);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }


    @GetMapping("/af/{af}")
    public ResponseEntity getItensByAf(@PathVariable("af") Long af) {
        List<ItensDTO> itens = service.getItensByAf(af);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/escola/{escola}/{pedido}")
    public ResponseEntity getItensByEscola(@PathVariable("escola") Long escola,@PathVariable("pedido") Long pedido) {
        List<ItensDTO> itens = service.getItensByEscola(escola,pedido);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    

    @GetMapping("/escolar/{escola}/{ano}")
    public ResponseEntity getEscolar(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getEscolar(escola,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/fornecedor/{fornecedor}")
    public ResponseEntity getByFornecedor(@PathVariable("fornecedor") Long fornecedor) {
        List<ItensDTO> itens = service.getByFornecedor(fornecedor);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/escolaAll/{ano}")
    public ResponseEntity getEscolaAll(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getEscolaAll(ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity getItensAno(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getItensAno(ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

   //verificado
    @GetMapping("/maispedidos/{ano}")
    public ResponseEntity getMaisPedidos(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getMaisPedidos(ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }
     //verificado
    @GetMapping("/totalMes/{ano}")
    public ResponseEntity getTotalMes(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalMes(ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }
    //verificado
    @GetMapping("/totalCategoria/{ano}")
    public ResponseEntity getTotalCategoria(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalCategoria(ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }



    @GetMapping("/totalMesNivel/{nivel}/{ano}")
    public ResponseEntity getTotalMesNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalMesNivel(nivel,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    //verificado
    @GetMapping("/totalMesEscola/{escola}/{ano}")
    public ResponseEntity getTotalMesEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalMesEscola(escola,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }





    @GetMapping("/totalCategoriaNivel/{nivel}/{ano}")
    public ResponseEntity getTotalCategoriaNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalCategoriaNivel(nivel,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    //verificado
    @GetMapping("/totalCategoriaEscola/{escola}/{ano}")
    public ResponseEntity getTotalCategoriaEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalCategoriaEscola(escola,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/totalEscolas/{ano}")
    public ResponseEntity getTotalEScolas(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalEscolas(ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/totalEscolaNivel/{nivel}/{ano}")
    public ResponseEntity getTotalEScolaNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalEscolaNivel(nivel,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    //verificado
    @GetMapping("/mediaAlunos/{ano}")
    public ResponseEntity getMediaAlunos(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getMediaAlunos(ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/mediaAlunosNivel/{nivel}/{ano}")
    public ResponseEntity getMediaAlunosNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getMediaAlunosNivel(nivel,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/produtos/{produto}/{ano}")
    public ResponseEntity getProdutos(@PathVariable("produto") Long produto,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getProdutos(produto,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }



    //verificado
    @GetMapping("/total/{ano}")
    public double getTotal(@PathVariable("ano") Long ano) {
        return service.getTotal(ano);
    }

//verificado
    @GetMapping("/tradicional/{ano}")
    public double getTradicional(@PathVariable("ano") Long ano) {
        return service.getTradicional(ano);
    }

    //verificado
    @GetMapping("/familiar/{ano}")
    public double getFamiliar(@PathVariable("ano") Long ano) {
        return service.getFamiliar(ano);
    }
    //verificado
    @GetMapping("/familiarEscola/{escola}/{ano}")
    public double getFamiliar(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        return service.getFamiliarEscola(escola,ano);
    }


//verificado
    @GetMapping("/totalEscola/{escola}/{ano}")
    public double getTotalEscola(@PathVariable("escola") Long escola, @PathVariable("ano") Long ano) {
        return service.getTotalEscola(escola,ano);
    }

    @GetMapping("/somaAll/{escola}")
    public double getRep(@PathVariable("escola") Long escola) {
        return service.getSoma(escola);
    }

    @GetMapping("/totalAgroEscola/{escola}/{ano}")
    public double getTotalAgroEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        return service.getTotalAgroEscola(escola,ano);
    }

    @GetMapping("/totalAgroNivel/{escola}/{ano}")
    public double getTotalAgroNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        return service.getTotalAgroNivel(nivel,ano);
    }

    @GetMapping("/totalAgro/{ano}")
    public double getTotalAgro(@PathVariable("ano") Long ano) {
        return service.getTotalAgro(ano);
    }

    @GetMapping("/totalPedido/{pedido}")
    public double getTotalPedido(@PathVariable("pedido") Long pedido) {
        return service.getTotalPedido(pedido);
    }

    @GetMapping("/totalAf/{af}")
    public double getTotalAf(@PathVariable("af") Long af) {
        return service.getTotalAf(af);
    }



    @GetMapping("/tradicionalNivel/{nivel}/{ano}")
    public double getTradicionalNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        return service.getTradicionalNivel(nivel,ano);
    }
    //verificado
    @GetMapping("/tradicionalEscola/{escola}/{ano}")
    public double getTradicionalEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        return service.getTradicionalEscola(escola,ano);
    }


    @GetMapping("/diversos/{ano}")
    public double getDiversos(@PathVariable("ano") Long ano) {
        return service.getDiversos(ano);
    }


    @GetMapping("/diversosNivel/{nivel}/{ano}")
    public double getDiversosNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        return service.getDiversosNivel(nivel,ano);
    }


    @GetMapping("/diversosEscola/{escola}/{ano}")
    public double getDiversosEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        return service.getDiversosEscola(escola,ano);
    }

    @GetMapping("/cart/{escola}")
    public double getCart(@PathVariable("escola") Long escola) {
        return service.getCart(escola);
    }

    @GetMapping("/estoque/{id}")
    public double getEstoque(@PathVariable("id") Long id) {
        return service.getEstoque(id);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody Itens pedidoItens) {

        ItensDTO c = service.insert(pedidoItens);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Itens pedidoItens) {
        pedidoItens.setId(id);
        ItensDTO c = service.update(pedidoItens, id);
        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
