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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.TipoUsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioPasswordInvalidException;
import dan.ms.tp.msusuarios.exception.UsuarioUsernameDuplicadoException;
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
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(usuarioService.getUsuario(id));
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Usuario>> getUsuariosByIdCliente(@PathVariable Integer idCliente, @RequestParam(name = "tipo", required = false) Integer idTipoUsuario) {
        
        if(idTipoUsuario == null) {
            try {
               return ResponseEntity.ok().body(usuarioService.getUsuariosByCliente(idCliente));
             } catch (ClienteNoEncontradoException e) {
              return ResponseEntity.notFound().build();
             }
        }
        
        try {
            return ResponseEntity.ok().body(usuarioService.getUsuariosOfTipoUsuarioByCliente(idTipoUsuario, idCliente));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (TipoUsuarioNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }

    }

    
    // @GetMapping("/cliente/{id}") 
    // public ResponseEntity<List<Usuario>> getUsuariosOfTipoUsuarioByIdCliente(, @RequestParam(name = "cliente") Integer idCliente) {

        // try {
        //     return ResponseEntity.ok().body(usuarioService.getUsuariosOfTipoUsuarioByCliente(idTipoUsuario, idCliente));
        // } catch (ClienteNoEncontradoException e) {
        //     return ResponseEntity.notFound().build();
        // } catch (TipoUsuarioNoEncontradoException e) {
        //     return ResponseEntity.notFound().build();
        // }
           
    // }
    

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok().body(usuarioService.createUsuario(usuario));
        // } catch (UsuarioDuplicadoException e){ // Actualmente es imposible esta excepción
        //     return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        } catch (UsuarioUsernameDuplicadoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build(); // TODO: Ver código
        } catch (UsuarioPasswordInvalidException e) {
             return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(usuario, id);
            return ResponseEntity.ok().body("El usuario "+updatedUsuario.getId()+" se actualizó exitosamente.");
        } catch (UsuarioUsernameDuplicadoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e.getMessage());
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e.getMessage()); // TODO: Ver código
        } catch (UsuarioPasswordInvalidException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
