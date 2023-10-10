package dan.ms.tp.msusuarios.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioDuplicadoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoAsociadoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
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
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Usuario> getUsuarioByIdCliente(@PathVariable Integer idCliente) {
        //TODO: REVISAR
        try {
            return ResponseEntity.ok().body(usuarioService.getUsuarioByCliente(idCliente));
        } catch (UsuarioNoAsociadoException e) {
            return ResponseEntity.notFound().build();
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }

    }

    /*ESTA NO LA ENTENDI, dice por tipo de usuario y ciente

    @GetMapping("/{tipoUsuario}/")
    public ResponseEntity<List<Usuario>> getAllUsuarioByTipoUsuario(@PathVariable String tipoUsuario) {

        return ResponseEntity.ok().body(usuarioService.findAllByTipoUsuario(tipoUsuario));
    }
    */

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok().body(usuarioService.addOrUpdateUsuario(usuario, null));
        } catch (UsuarioDuplicadoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        } catch (UsuarioNoEncontradoException e) {
            // no deberia darse nunca. el fix a esto es dividir el add or update en dos metodos.
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario nuevoUsuario, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(usuarioService.addOrUpdateUsuario(nuevoUsuario, id));
        } catch (UsuarioDuplicadoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        
        try {
            usuarioService.deleteUsuario(id);
        } catch (UsuarioNoEncontradoException e) {
            // TODO como devolver un response ??
            e.printStackTrace();
        }
    }
}
