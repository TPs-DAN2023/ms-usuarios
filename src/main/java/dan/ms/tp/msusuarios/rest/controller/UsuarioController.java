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
import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.rest.service.UsuarioService;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuario() {
        return ResponseEntity.ok().body(usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(usuarioService.getUsuarioById(id));
        } catch (Exception e) {
            // TODO: handle exception RecursoNoEncontrado
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Usuario> getUsuarioByIdCliente(@PathVariable Integer idCliente) {
        
        try {
            return ResponseEntity.ok().body(usuarioService.getUsuarioByCliente(idCliente));
        } catch (Exception e) {
            // TODO: handle exception RecursoNoEncontrado
        }

        return ResponseEntity.notFound().build();
    }

    /*ESTA NO LA ENTENDI, dice por tipo de usuario y ciente

    @GetMapping("/{tipoUsuario}/")
    public ResponseEntity<List<Usuario>> getAllUsuarioByTipoUsuario(@PathVariable String tipoUsuario) {

        return ResponseEntity.ok().body(usuarioService.findAllByTipoUsuario(tipoUsuario));
    }
    */

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.saveOrUpdateUsuario(usuario, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario nuevoUsuario, @PathVariable Integer id) {
        
        return ResponseEntity.ok().body(usuarioService.saveOrUpdateUsuario(nuevoUsuario, id));
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);  //Ver si le agregamos un return codigo 204?
    }
}
