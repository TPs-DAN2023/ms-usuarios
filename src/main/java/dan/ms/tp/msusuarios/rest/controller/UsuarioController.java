package dan.ms.tp.msusuarios.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.modelo.Usuario;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioJpaRepository usuarioRepo;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario() {
        return ResponseEntity.ok().body(usuarioRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(usuarioRepo.findById(id).orElseThrow(() -> new Exception()));
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Usuario> getUsuarioByIdCliente(@PathVariable Integer idCliente) {
        
        try {

            return ResponseEntity.ok().body(usuarioRepo.findByIdCliente(idCliente));
        } catch (Exception e) {
            // TODO: handle exception
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{tipoUsuario}")
    public ResponseEntity<List<Usuario>> getAllUsuarioByTipoUsuario(@PathVariable String tipoUsuario) {

        return ResponseEntity.ok().body(usuarioRepo.findAllByTipoUsuario(tipoUsuario));
    }

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioRepo.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario nuevoUsuario, @PathVariable Integer id) {
        
        Usuario usuario = null;
        try {
            usuario = usuarioRepo.findById(id).orElseThrow(() -> new Exception());
            usuario.setUserName(nuevoUsuario.getUserName());
            usuario.setCliente(nuevoUsuario.getCliente());
            usuario.setCorreoElectronico(nuevoUsuario.getCorreoElectronico());
            usuario.setPassword(nuevoUsuario.getPassword());
            usuario.setTipoUsuario(nuevoUsuario.getTipoUsuario());
            
        } catch (Exception e) {
            nuevoUsuario.setId(id);
            usuario = nuevoUsuario;
        }

        return ResponseEntity.ok().body(usuarioRepo.save(usuario));
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {

        usuarioRepo.deleteById(id);  //Ver si le agregamos un return codigo 204?
    }
}
