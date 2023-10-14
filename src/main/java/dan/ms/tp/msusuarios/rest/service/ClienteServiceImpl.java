package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.exception.ClienteMailDuplicadoException;
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

    Cliente c = clienteRepo.findClienteByCuit(cuit);

    if (c == null) {
      throw new ClienteNoEncontradoException(cuit);
    }

    return c;
  }

  @Override
  public Cliente createCliente(Cliente cliente) throws ClienteMailDuplicadoException {

    if (esMailRepetido(cliente.getCorreoElectronico())) {
      throw new ClienteMailDuplicadoException(cliente.getCorreoElectronico());
    }

    return clienteRepo.save(cliente);
  }

  @Override
  public void updateCliente(Cliente cliente, Integer id) throws ClienteNoEncontradoException,
    ClienteMailDuplicadoException {

    Optional<Cliente> c = clienteRepo.findById(id);

    if (!c.isPresent()) {
      throw new ClienteNoEncontradoException(id);
    }

    Cliente clienteViejo = c.get();

    if (esMailRepetido(cliente.getCorreoElectronico())) {
      throw new ClienteMailDuplicadoException(clienteViejo.getCorreoElectronico());
    }
      
    clienteViejo.setCorreoElectronico(cliente.getCorreoElectronico());
    clienteViejo.setHabilitadoOnline(cliente.getHabilitadoOnline());
    clienteViejo.setMaximoCuentaCorriente(cliente.getMaximoCuentaCorriente());
    clienteViejo.setRazonSocial(cliente.getRazonSocial());
    clienteViejo.setCuit(cliente.getCuit());

    clienteRepo.save(clienteViejo);
  }

  @Override
  public void deleteCliente(Integer id) throws ClienteNoEncontradoException {
    if(!clienteRepo.existsById(id)) {
      throw new ClienteNoEncontradoException(id);
    }

    clienteRepo.deleteById(id);
  }

  // Filtra entre todos los clientes alguno que tenga mismo mail que el pasado por parámetro
  private boolean esMailRepetido(String mail) {
    return clienteRepo.findAll().stream().filter(c -> c.getCorreoElectronico().equals(mail)).findAny().isPresent();
  }
}
