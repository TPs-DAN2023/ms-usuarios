package dan.ms.tp.msusuarios.rest.controller;

import java.util.List;
import java.util.Optional;

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

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.modelo.Cliente;

@RestController
@RequestMapping("api/cliente")
public class ClienteController {
    
    @Autowired
    ClienteJpaRepository clienteRepo;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllCliente() {
        return ResponseEntity.ok().body(clienteRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cliente>> getClienteById(@RequestParam Integer id) {
        return ResponseEntity.ok().body(clienteRepo.findById(id));
    }

    @GetMapping("/{cuit}")
    public ResponseEntity<Cliente> getClienteByCuit(@RequestParam String cuit) {

        return ResponseEntity.ok().body(clienteRepo.findByCuit(cuit));
    }

    @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok().body(clienteRepo.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> putCliente(@RequestBody Cliente nuevoCliente, @PathVariable Integer id) {
        
        Cliente cliente = null;
        try {
            cliente = clienteRepo.findById(id).orElseThrow(() -> new Exception());
            cliente.setCuit(nuevoCliente.getCuit());
            cliente.setHabilitadoOnline(nuevoCliente.getHabilitadoOnline());
            cliente.setCorreoElectronico(nuevoCliente.getCorreoElectronico());
            cliente.setMaximoCuentaCorriente(nuevoCliente.getMaximoCuentaCorriente());
            cliente.setRazonSocial(nuevoCliente.getRazonSocial());            
        } catch (Exception e) {
            nuevoCliente.setId(id);
            cliente = nuevoCliente;
        }

        return ResponseEntity.ok().body(clienteRepo.save(cliente));
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Integer id) {

        clienteRepo.deleteById(id);  //Ver si le agregamos un return codigo 204?
    }
}
