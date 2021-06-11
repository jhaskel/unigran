package com.merenda.merenda.api.produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<ProdutoDTO> produtos = service.getProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        ProdutoDTO produto = service.getProdutoById(id);
        return ResponseEntity.ok(produto);
    }


    @GetMapping("/escola/{escola}")
    public ResponseEntity getProdutosByEscola(@PathVariable("escola") Long escola) {
        List<ProdutoDTO> produtos = service.getProdutosByEscola(escola);
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getId(@PathVariable("id") Long id) {
        List<ProdutoDTO> produtos = service.getId(id);
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos);
    }

    @GetMapping("/menos")
    public ResponseEntity getMenos() {
        List<ProdutoDTO> produtos = service.getMenos();
        return produtos.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(produtos);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody Produto produto) {
        ProdutoDTO c = service.insert(produto);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Produto produto) {

        produto.setId(id);

        ProdutoDTO c = service.update(produto, id);

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
