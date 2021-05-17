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
        List<AfDTO> afs = service.getAf();
        return ResponseEntity.ok(afs);
    }
    public ResponseEntity getAll() {
        List<AfDTO> afs = service.getAll();
        return ResponseEntity.ok(afs);
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        AfDTO af = service.getAfById(id);

        return ResponseEntity.ok(af);
    }

   //busca as af por fornecedores
    @GetMapping("/fornecedor/{fornecedor}")
    public ResponseEntity getByFornecedor(@PathVariable("fornecedor") Long fornecedor) {
        List<AfDTO> afs = service.getByFornecedor(fornecedor);
        return afs.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(afs);
    }

   //busca as af pelo code
    @GetMapping("/af/{af}")
    public ResponseEntity getByAf(@PathVariable("af") Long af) {
        List<AfDTO> afs = service.getByAf(af);
        return afs.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(afs);
    }


    //soma o total e afs eviadas para os fornecedores
    @GetMapping("/afEnviada")
    public long getAfEnviada() {
        return service.getAfEnviada();
    }




    //insere no banco
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

    //edita no banco
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Af af) {
        af.setId(id);
        AfDTO c = service.update(af, id);
        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    //deleta no banco
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
