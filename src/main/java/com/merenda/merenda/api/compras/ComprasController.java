package com.merenda.merenda.api.compras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/compras")
public class ComprasController {
    @Autowired
    private ComprasService service;


    @GetMapping()
    public ResponseEntity get() {
        List<ComprasDTO> carros = service.getCompras();
        return ResponseEntity.ok(carros);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody Compras compras) {

        ComprasDTO c = service.insert(compras);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Compras compras) {
        compras.setId(id);
        ComprasDTO c = service.update(compras, id);
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
