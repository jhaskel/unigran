package com.merenda.merenda.api.af;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/af")
public class AfController {
    @Autowired
    private AfService service;


    @GetMapping()
    public ResponseEntity get() {
        List<AfDTO> carros = service.getCarros();
        return ResponseEntity.ok(carros);
    }



    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        AfDTO carro = service.getCarroById(id);

        return ResponseEntity.ok(carro);
    }

    @GetMapping("/fornecedor/{fornecedor}")
    public ResponseEntity getByFornecedor(@PathVariable("fornecedor") Long fornecedor) {
        List<AfDTO> carros = service.getByFornecedor(fornecedor);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/af/{af}")
    public ResponseEntity getByAf(@PathVariable("af") Long af) {
        List<AfDTO> carros = service.getByAf(af);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/af")
    public long getAf() {
        return service.getAf();
    }




    @PostMapping
    public ResponseEntity post(@RequestBody Af af) {

        AfDTO c = service.insert(af);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Af af) {
        af.setId(id);
        AfDTO c = service.update(af, id);
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
