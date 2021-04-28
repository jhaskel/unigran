package com.merenda.merenda.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private com.merenda.merenda.api.users.UserService service;

    @GetMapping()
    public ResponseEntity get() {
        List<UserDTO> list = service.getUsers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/info")
    public UserDTO userInfo(@AuthenticationPrincipal User user) {

        //UserDetails userDetails = JwtUtil.getUserDetails();

        return UserDTO.create(user);
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        UserDTO rota = service.getUserById(id);

        return ResponseEntity.ok(rota);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody User user) {
        UserDTO c = service.insert(user);
        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody User user) {

        user.setId(id);

        UserDTO c = service.update(user, id);

        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

}