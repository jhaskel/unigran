package com.merenda.merenda.api.af.afAdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/afAdd")
public class AfAddController {
    @Autowired
    private AfAddService service;

    @GetMapping()
    public ResponseEntity get() {
        List<AfAddDTO> afAdds = service.getAfAdds();
        return ResponseEntity.ok(afAdds);    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        AfAddDTO carro = service.getAfAddById(id);
        return ResponseEntity.ok(carro);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody AfAdd afAdd) {

        AfAddDTO c = service.insert(afAdd);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }


    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody AfAdd afAdd) {
        afAdd.setId(id);
        AfAddDTO c = service.update(afAdd, id);
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
