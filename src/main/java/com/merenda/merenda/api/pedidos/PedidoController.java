package com.merenda.merenda.api.pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;


    @GetMapping()
    public ResponseEntity get() {
        List<PedidoDTO> pedido = service.getPedido();
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        PedidoDTO pedido = service.getPedidoById(id);

        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity getPedidoByCode(@PathVariable("code") String code) {
        List<PedidoDTO> pedido = service.getPedidoByCode(code);
        return pedido.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(pedido);
    }

    @GetMapping("/escola/{escola}")
    public ResponseEntity getPedidoByEscola(@PathVariable("escola") Long escola) {
        List<PedidoDTO> pedido = service.getPedidoByEscola(escola);
        return pedido.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(pedido);
    }



    @GetMapping("/pedidoSemAf")
    public long getPedidoSemAf() {
        return service.getPedidoSemAf();
    }


    @PostMapping

    public ResponseEntity post(@RequestBody Pedido pedido) {

        PedidoDTO c = service.insert(pedido);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }


    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        PedidoDTO c = service.update(pedido, id);
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
