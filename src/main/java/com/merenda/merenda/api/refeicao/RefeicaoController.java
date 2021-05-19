package com.merenda.merenda.api.refeicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/refeicao")
public class RefeicaoController {
    @Autowired
    private RefeicaoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<RefeicaoDTO> refeicao = service.getRefeicao();
        return ResponseEntity.ok(refeicao);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        RefeicaoDTO refeicao = service.getRefeicaoById(id);
        return ResponseEntity.ok(refeicao);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity getFornecId(@PathVariable("id") Long id) {
        List<RefeicaoDTO> refeicao = service.getFornecId(id);
        return refeicao.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(refeicao);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Refeicao refeicao) {
        RefeicaoDTO c = service.insert(refeicao);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Refeicao refeicao) {
        refeicao.setId(id);
        RefeicaoDTO c = service.update(refeicao, id);
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
