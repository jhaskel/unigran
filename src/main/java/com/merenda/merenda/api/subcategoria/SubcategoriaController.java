package com.merenda.merenda.api.subcategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subcategorias")
public class SubcategoriaController {
    @Autowired
    private SubcategoriaService service;


    @GetMapping()
    public ResponseEntity get() {
        List<SubcategoriaDTO> categorias = service.getCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        SubcategoriaDTO categoria = service.getCategoriaById(id);

        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getCategoriaId(@PathVariable("id") Long id) {
        List<SubcategoriaDTO> categoria = service.getCategoriaId(id);
        return categoria.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(categoria);
    }





    @PostMapping

    public ResponseEntity post(@RequestBody Subcategoria subcategoria) {

        SubcategoriaDTO c = service.insert(subcategoria);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Subcategoria subcategoria) {

        subcategoria.setId(id);

        SubcategoriaDTO c = service.update(subcategoria, id);

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
