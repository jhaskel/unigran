package com.merenda.merenda.api.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estoque")
public class EstoqueController {
    @Autowired
    private EstoqueService service;


    @GetMapping()
    public ResponseEntity get() {
        List<EstoqueDTO> produtos = service.getProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        EstoqueDTO produto = service.getProdutoById(id);
        return ResponseEntity.ok(produto);
    }


    @GetMapping("/escola/{escola}")
    public ResponseEntity getProdutosByEscola(@PathVariable("escola") Long escola) {
        List<EstoqueDTO> produtos = service.getProdutosByEscola(escola);
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getId(@PathVariable("id") Long id) {
        List<EstoqueDTO> produtos = service.getId(id);
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos);
    }

    @GetMapping("/menos")
    public ResponseEntity getMenos() {
        List<EstoqueDTO> produtos = service.getMenos();
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody Estoque estoque) {
        EstoqueDTO c = service.insert(estoque);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Estoque estoque) {

        estoque.setId(id);

        EstoqueDTO c = service.update(estoque, id);

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
