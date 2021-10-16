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
        List<CartDTO> carts = service.getCart();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        CartDTO cart = service.getCartById(id);

        return ResponseEntity.ok(cart);
    }


    @GetMapping("/local/{local}")
    public ResponseEntity getCartsByLocal(@PathVariable("local") Long local) {
        List<CartDTO> carts = service.getCartsByLocal(local);
        return carts.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carts);
    }

    @GetMapping("/itensCart/{local}")
    public double getRep(@PathVariable("local") Long local) {
        return service.getSoma(local);
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
