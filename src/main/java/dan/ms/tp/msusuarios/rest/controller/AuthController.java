package dan.ms.tp.msusuarios.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.modelo.Usuario;

@RestController("/api/auth")
public class AuthController {
    
    @PostMapping("/login")    
    public ResponseEntity<Usuario> login(@RequestBody Usuario credentials) {
        //TODO
        return null;
    }

    @PostMapping("/register")    
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario user) {
        //TODO
        return null;
    }
}