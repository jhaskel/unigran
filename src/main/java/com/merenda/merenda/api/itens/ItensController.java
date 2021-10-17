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

    //verificado
    @GetMapping("/totalMesLocal/{local}/{ano}")
    public ResponseEntity getTotalMesLocal(@PathVariable("local") Long local,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalMesLocal(local,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }


    //verificado
    @GetMapping("/totalCategoriaLocal/{local}/{ano}")
    public ResponseEntity getTotalCategoriaLocal(@PathVariable("local") Long local,@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalCategoriaLocal(local,ano);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }

    @GetMapping("/totalLocals/{ano}")
    public ResponseEntity getTotalEScolas(@PathVariable("ano") Long ano) {
        List<ItensDTO> itens = service.getTotalLocals(ano);
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

    //verificado
    @GetMapping("/produto/{produto}")
    public ResponseEntity getProduto(@PathVariable("produto") Long produto) {
        List<ItensDTO> itens = service.getProduto(produto);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }
    //verificado
    @GetMapping("/produto2/{produto}")
    public ResponseEntity getProduto2(@PathVariable("produto") Long produto) {
        List<ItensDTO> itens = service.getProduto2(produto);
        return itens.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(itens);
    }


    //verificado testado
    @GetMapping("/total/{ano}")
    public double getTotal(@PathVariable("ano") Long ano) {
        return service.getTotal(ano);
    }

    //verificado testado
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
    @GetMapping("/familiarLocal/{local}/{ano}")
    public double getFamiliar(@PathVariable("local") Long local,@PathVariable("ano") Long ano) {
        return service.getFamiliarLocal(local,ano);
    }
   //verificado
    @GetMapping("/totalLocal/{local}/{ano}")
    public double getTotalLocal(@PathVariable("local") Long local, @PathVariable("ano") Long ano) {
        return service.getTotalLocal(local,ano);
    }



    //verificado
    @GetMapping("/tradicionalLocal/{local}/{ano}")
    public double getTradicionalLocal(@PathVariable("local") Long local,@PathVariable("ano") Long ano) {
        return service.getTradicionalLocal(local,ano);
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
