package com.merenda.merenda.api.setor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/setor")
public class SetorController {
    @Autowired
    private SetorService service;


    @GetMapping()
    public ResponseEntity get() {
        List<SetorDTO> categorias = service.getCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        SetorDTO categoria = service.getCategoriaById(id);

        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getCategoriaId(@PathVariable("id") Long id) {
        List<SetorDTO> categoria = service.getCategoriaId(id);
        return categoria.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(categoria);
    }




    @PostMapping

    public ResponseEntity post(@RequestBody Setor setor) {

        SetorDTO c = service.insert(setor);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Setor setor) {

        setor.setId(id);

        SetorDTO c = service.update(setor, id);

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
