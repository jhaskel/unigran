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
        List<ProdutoDTO> carros = service.getCarros();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        ProdutoDTO carro = service.getCarroById(id);

        return ResponseEntity.ok(carro);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity getCarrosByCode(@PathVariable("code") String code) {
        List<ProdutoDTO> carros = service.getCarrosByCode(code);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/escola/{escola}")
    public ResponseEntity getCarrosByEscola(@PathVariable("escola") Long escola) {
        List<ProdutoDTO> carros = service.getCarrosByEscola(escola);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getId(@PathVariable("id") Long id) {
        List<ProdutoDTO> carros = service.getId(id);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/menos")
    public ResponseEntity getMenos() {
        List<ProdutoDTO> carros = service.getMenos();
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
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
