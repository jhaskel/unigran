package com.merenda.merenda.api.afPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/afpedido")
public class AfPedidoController {
    @Autowired
    private AfPedidoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<AfPedidoDTO> afPedidos = service.getAfPedidos();
        return ResponseEntity.ok(afPedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        AfPedidoDTO afPedido = service.getAfPedidoById(id);

        return ResponseEntity.ok(afPedido);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody AfPedido afPedido) {

        AfPedidoDTO c = service.insert(afPedido);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody AfPedido afPedido) {
        afPedido.setId(id);
        AfPedidoDTO c = service.update(afPedido, id);
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
