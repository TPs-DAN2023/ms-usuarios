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

import dan.ms.tp.msusuarios.exception.ClienteMailDuplicadoException;
import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.ClienteUsuariosInvalidException;
import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.rest.service.ClienteService;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllCliente() {
        return ResponseEntity.ok().body(clienteService.getAllClientes());
    }

    // TODO: Preguntar a profes como hacer con el tipo de retorno de los ResponseEntitiy si hay excepción
    @GetMapping("/{id}")
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(clienteService.getCliente(id));
        } catch (ClienteNoEncontradoException e) {
            //return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Cliente> getClienteByCuit(@RequestParam(name = "cuit", required = false) String cuit) {
        try {
            return ResponseEntity.ok().body(clienteService.getCliente(cuit));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente) {
        // YO CAMBIARIA a void /// peiretti // Yo creo que esta bueno devolverlo (? /// julito
        try {
            return ResponseEntity.ok().body(clienteService.createCliente(cliente));
        // } catch (ClienteDuplicadoException e){ // Actualmente es imposible esta excepción
        //     return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        } catch (ClienteMailDuplicadoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        } catch (ClienteUsuariosInvalidException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCliente(@RequestBody Cliente cliente, @PathVariable Integer id) {
        try {
            Cliente updatedClient = clienteService.updateCliente(cliente, id);
            return ResponseEntity.ok().body("El cliente "+updatedClient.getId()+" se actualizó exitosamente.");
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
        } catch (ClienteMailDuplicadoException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e.getMessage());
        } catch (ClienteUsuariosInvalidException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(409)).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        try {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
