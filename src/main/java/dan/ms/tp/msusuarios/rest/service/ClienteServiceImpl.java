package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.modelo.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService{

  @Autowired
  ClienteJpaRepository clienteRepo;

  @Override
  public List<Cliente> getAllClientes() {
    return clienteRepo.findAll();
  }

  @Override
  public Cliente getCliente(Integer id) throws ClienteNoEncontradoException {
    Optional<Cliente> c = clienteRepo.findById(id);
    
    if(!c.isPresent()){
      throw new ClienteNoEncontradoException(id);
    }

    return c.get();
  }

  @Override
  public Cliente getCliente(String cuit) throws ClienteNoEncontradoException {

    Cliente c = clienteRepo.findByCuit(cuit);

    if(c == null) throw new ClienteNoEncontradoException(cuit);
    
    return c;
  }

  @Override
  public Cliente addOrUpdateCliente(Cliente cliente, Integer id) {
    Optional<Cliente> c = clienteRepo.findById(id);
  
    if(!c.isPresent()){
      return clienteRepo.save(cliente);
    }

    Cliente viejo = c.get();
    viejo.setCorreoElectronico(cliente.getCorreoElectronico());
    viejo.setHabilitadoOnline(cliente.getHabilitadoOnline());
    viejo.setMaximoCuentaCorriente(cliente.getMaximoCuentaCorriente());
    viejo.setRazonSocial(cliente.getRazonSocial());
    viejo.setCuit(cliente.getCuit());

    return clienteRepo.save(viejo);

  }

  @Override
  public void deleteCliente(Integer id) {
    //si no lo encuentra lo ignora.
    clienteRepo.deleteById(id);
  }
  
}
