package com.merenda.merenda.api.categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;


    @GetMapping()
    public ResponseEntity get() {
        List<CategoriaDTO> categorias = service.getCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        CategoriaDTO categoria = service.getCategoriaById(id);

        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getCategoriaId(@PathVariable("id") Long id) {
        List<CategoriaDTO> categoria = service.getCategoriaId(id);
        return categoria.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(categoria);
    }




    @PostMapping

    public ResponseEntity post(@RequestBody Categoria categoria) {

        CategoriaDTO c = service.insert(categoria);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Categoria categoria) {

        categoria.setId(id);

        CategoriaDTO c = service.update(categoria, id);

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
