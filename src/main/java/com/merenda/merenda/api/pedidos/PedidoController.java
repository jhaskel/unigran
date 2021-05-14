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
        List<PedidoDTO> carros = service.getCarros();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        PedidoDTO carro = service.getCarroById(id);

        return ResponseEntity.ok(carro);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity getCarrosByCode(@PathVariable("code") String code) {
        List<PedidoDTO> carros = service.getCarrosByCode(code);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/escola/{escola}")
    public ResponseEntity getCarrosByEscola(@PathVariable("escola") Long escola) {
        List<PedidoDTO> carros = service.getCarrosByEscola(escola);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
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

    @GetMapping("/ultimoid")
    public long getUltimoId() {
        return service.getUltimoId();
    }


    @GetMapping("/temcart/{escola}")
    public long getTemCart(@PathVariable("escola") Long escola) {
        return service.getTemCart(escola);
    }

    @GetMapping("/temcart1/{escola}")
    public long getTemCart1(@PathVariable("escola") Long escola) {
        return service.getTemCart1(escola);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getCId(@PathVariable("id") Long id) {
        List<PedidoDTO> carros = service.getId(id);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
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
