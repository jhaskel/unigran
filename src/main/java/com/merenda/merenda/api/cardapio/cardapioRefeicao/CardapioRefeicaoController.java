package com.merenda.merenda.api.cardapio.cardapioRefeicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pratos")
public class CardapioRefeicaoController {
    @Autowired
    private CardapioRefeicaoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<CardapioRefeicaoDTO> cardapio = service.getCardapio();
        return ResponseEntity.ok(cardapio);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        CardapioRefeicaoDTO cardapio = service.getCardapioById(id);
        return ResponseEntity.ok(cardapio);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity getFornecId(@PathVariable("id") Long id) {
        List<CardapioRefeicaoDTO> cardapio = service.getFornecId(id);
        return cardapio.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(cardapio);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody CardapioRefeicao cardapioRefeicao) {
        CardapioRefeicaoDTO c = service.insert(cardapioRefeicao);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody CardapioRefeicao cardapioRefeicao) {
        cardapioRefeicao.setId(id);
        CardapioRefeicaoDTO c = service.update(cardapioRefeicao, id);
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
