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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
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

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@RequestParam Integer id) {

        try {
            return ResponseEntity.ok().body(clienteService.getCliente(id));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{cuit}")
    public ResponseEntity<Cliente> getClienteByCuit(@RequestParam String cuit) {
        try {
        return ResponseEntity.ok().body(clienteService.getCliente(cuit));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente) {
        // YO CAMBIARIA a void /// peiretti
        return ResponseEntity.ok().body(clienteService.addOrUpdateCliente(cliente, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> putCliente(@RequestBody Cliente nuevoCliente, @PathVariable Integer id) {
        return ResponseEntity.ok().body(clienteService.addOrUpdateCliente(nuevoCliente, id));
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);  //Ver si le agregamos un return codigo 204? TODO
    }
}
