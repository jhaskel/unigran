package com.merenda.merenda.api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private com.merenda.merenda.api.usuario.UsuarioService service;

    @GetMapping()
    public ResponseEntity get() {
        List<com.merenda.merenda.api.usuario.UsuarioDTO> usuarios = service.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        UsuarioDTO usuario = service.getUsuarioById(id);

        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity getColetandoByCidade(@PathVariable("id") Long id) {
        List<UsuarioDTO> coletando = service.getTesteById(id);
        return coletando.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(coletando);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity getColetandoByEmail(@PathVariable("email") String email) {
        List<UsuarioDTO> coletando = service.getTesteByEmail(email);
        return coletando.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(coletando);
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity getColetandoByNivel(@PathVariable("nivel") String nivel) {
        List<UsuarioDTO> coletando = service.getTesteByNivel(nivel);
        return coletando.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(coletando);
    }



    @GetMapping("/quantnoticia/{usuario}")
    public double QuantNoticia(@PathVariable("usuario") Long usuario) {
        return service.QuantNoticia(usuario);
    }



    @GetMapping("/quantemail/{email}")
    public long QuantEmail(@PathVariable("email") String email ) {
        return service.QuantEmail(email);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody Usuario usuario) {
        UsuarioDTO c = service.insert(usuario);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Usuario usuario) {

        usuario.setId(id);

        UsuarioDTO c = service.update(usuario, id);

        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }
    @PutMapping("/id/{id}")
    public ResponseEntity put2(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        UsuarioDTO c = service.update(usuario, id);
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
