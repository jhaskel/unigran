package com.merenda.merenda.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/config")
public class ConfigController {
    @Autowired
    private ConfigService service;


    @GetMapping()
    public ResponseEntity get() {
        List<ConfigDTO> config = service.getConfig();
        return ResponseEntity.ok(config);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        ConfigDTO config = service.getConfigById(id);

        return ResponseEntity.ok(config);
    }

    @PostMapping

    public ResponseEntity post(@RequestBody Config config) {

        ConfigDTO c = service.insert(config);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Config config) {

        config.setId(id);

        ConfigDTO c = service.update(config, id);

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
