package com.merenda.merenda.api.cart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService service;


    @GetMapping()
    public ResponseEntity get() {
        List<CartDTO> carros = service.getCarros();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        CartDTO carro = service.getCarroById(id);

        return ResponseEntity.ok(carro);
    }


    @GetMapping("/escola/{escola}")
    public ResponseEntity getCarrosByEscola(@PathVariable("escola") Long escola) {
        List<CartDTO> carros = service.getCarrosByEscola(escola);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/itensCart/{escola}")
    public double getRep(@PathVariable("escola") Long escola) {
        return service.getSoma(escola);
    }



    @PostMapping

    public ResponseEntity post(@RequestBody Cart cart) {

        CartDTO c = service.insert(cart);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Cart cart) {

        cart.setId(id);

        CartDTO c = service.update(cart, id);

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
