package dan.ms.tp.msusuarios.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.rest.service.TipoUsuarioService;

@RestController
@RequestMapping("api/tipo-usuario")
public class TipoUsuarioController {
 
    @Autowired
    TipoUsuarioService tuService;


    @GetMapping
    public ResponseEntity<List<TipoUsuario>> getAllTipoUsuario(){
        return ResponseEntity.ok().body(tuService.getAllTipoUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> getTipoUsuarioById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(tuService.getTipoUsuario(id));
        } catch (Exception e) {
            // TODO: handle exception RecursoNoEncontrado
        }
        return ResponseEntity.notFound().build();
    }

}
