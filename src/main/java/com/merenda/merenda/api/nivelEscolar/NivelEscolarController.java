package com.merenda.merenda.api.nivelEscolar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/nivel")
public class NivelEscolarController {
    @Autowired
    private NivelEscolarService service;


    @GetMapping()
    public ResponseEntity get() {
        List<NivelEscolarDTO> nivel = service.getNivel();
        return ResponseEntity.ok(nivel);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        NivelEscolarDTO nivel = service.getNivelById(id);

        return ResponseEntity.ok(nivel);
    }

    @GetMapping("/nome/{id}")
    public String getRe(@PathVariable("id") Long id) {
        return service.getRe(id);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getId(@PathVariable("id") Long id) {
        List<NivelEscolarDTO> nivel = service.getId(id);
        return nivel.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(nivel);
    }



    @PostMapping

    public ResponseEntity post(@RequestBody NivelEscolar nivelEscolar) {
        NivelEscolarDTO c = service.insert(nivelEscolar);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody NivelEscolar nivelEscolar) {
        nivelEscolar.setId(id);
        NivelEscolarDTO c = service.update(nivelEscolar, id);
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
