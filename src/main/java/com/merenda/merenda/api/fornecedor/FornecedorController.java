package com.merenda.merenda.api.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService service;


    @GetMapping()
    public ResponseEntity get() {
        List<FornecedorDTO> fornecedor = service.getFornecedor();
        return ResponseEntity.ok(fornecedor);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        FornecedorDTO fornecedor = service.getFornecedorById(id);
        return ResponseEntity.ok(fornecedor);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity getFornecId(@PathVariable("id") Long id) {
        List<FornecedorDTO> fornecedor = service.getFornecId(id);
        return fornecedor.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(fornecedor);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Fornecedor fornecedor) {
        FornecedorDTO c = service.insert(fornecedor);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor) {
        fornecedor.setId(id);
        FornecedorDTO c = service.update(fornecedor, id);
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
