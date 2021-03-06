package com.merenda.merenda.api.unidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/unidades")
public class UnidadeController {
    @Autowired
    private UnidadeService service;


    @GetMapping()
    public ResponseEntity get() {
        List<UnidadeDTO> escolas = service.getNivel();
        return ResponseEntity.ok(escolas);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        UnidadeDTO escola = service.getNivelById(id);

        return ResponseEntity.ok(escola);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity getEscolasById(@PathVariable("id") Long id) {
        List<UnidadeDTO> escolas = service.getEscolasById(id);
        return escolas.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(escolas);
    }

    @GetMapping("/setor/{setor}")
    public ResponseEntity getSetor(@PathVariable("setor") Long setor) {
        List<UnidadeDTO> escolas = service.getSetor(setor);
        return escolas.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(escolas);
    }

    //verificado
    @GetMapping("/quantidade")
    public long getQuantidade() {
        return service.getQuantidade();
    }

    //verificado
    @GetMapping("/quantAlunos")
    public long getQuantAlunos() {
        return service.getQuantAlunos();
    }


    //verificado
    @GetMapping("/quantAlunosEscola/{id}")
    public long getQuantAlunosEscola(@PathVariable("id") Long id) {
        return service.getQuantAlunosEscola(id);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody Unidade unidade) {

        UnidadeDTO c = service.insert(unidade);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Unidade unidade) {

        unidade.setId(id);

        UnidadeDTO c = service.update(unidade, id);

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
