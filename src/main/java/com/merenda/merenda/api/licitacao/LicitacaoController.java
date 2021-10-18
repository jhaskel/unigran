package com.merenda.merenda.api.licitacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/licitacao")
public class LicitacaoController {
    @Autowired
    private LicitacaoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<LicitacaoDTO> carts = service.getCart();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        LicitacaoDTO cart = service.getCartById(id);
        return ResponseEntity.ok(cart);
    }


    @GetMapping("/processo/{processo}")
    public ResponseEntity getProcesso(@PathVariable("processo") String processo) {
        List<LicitacaoDTO> carts = service.getProcesso(processo);
        return carts.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carts);
    }


    @PostMapping

    public ResponseEntity post(@RequestBody Licitacao licitacao) {

        LicitacaoDTO c = service.insert(licitacao);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Licitacao licitacao) {

        licitacao.setId(id);

        LicitacaoDTO c = service.update(licitacao, id);

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
