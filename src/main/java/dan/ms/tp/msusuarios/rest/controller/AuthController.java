package dan.ms.tp.msusuarios.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.rest.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    

    @PostMapping("/login")    
    public ResponseEntity<Usuario> login(@RequestBody Usuario credentials) {
        try {
            return ResponseEntity.ok().body(authService.login(credentials.getUserName(), credentials.getPassword()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")    
    public ResponseEntity<Usuario> register(@RequestBody Usuario user) {
        try {
            return ResponseEntity.ok().body(authService.register(user));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        boolean isValid = authService.isTokenValid(token);
        return isValid ? ResponseEntity.ok().body("Token valido") : ResponseEntity.badRequest().body("Token invalido");
    }
    
}