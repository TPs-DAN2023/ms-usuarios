package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.ClienteMailDuplicadoException;
import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.ClienteUsuariosInvalidException;
import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public class ClienteServiceImpl implements ClienteService {

  @Autowired
  ClienteJpaRepository clienteRepo;

  @Autowired
  UsuarioJpaRepository usuarioRepo;

  @Override
  public List<Cliente> getAllClientes() {
    return clienteRepo.findAll();
  }

  @Override
  public Cliente getCliente(Integer id) throws ClienteNoEncontradoException {
    Optional<Cliente> c = clienteRepo.findById(id);

    if (c.isEmpty()) {
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
  public Cliente createCliente(Cliente cliente) throws ClienteMailDuplicadoException, ClienteUsuariosInvalidException {

    if (esMailRepetido(cliente.getCorreoElectronico())) {
      throw new ClienteMailDuplicadoException(cliente.getCorreoElectronico());
    }

    if (!areUsuariosValid(cliente.getUsuarios())) {
      throw new ClienteUsuariosInvalidException(cliente.getUsuarios());
    }

    return clienteRepo.save(cliente);
  }

  @Override
  public Cliente updateCliente(Cliente cliente, Integer id) throws ClienteNoEncontradoException,
      ClienteMailDuplicadoException, ClienteUsuariosInvalidException {

    Optional<Cliente> c = clienteRepo.findById(id);

    if (c.isEmpty()) {
      throw new ClienteNoEncontradoException(id);
    }

    Cliente clienteViejo = c.get();

    Boolean hasSameEmail = (cliente.getCorreoElectronico() != null
        && cliente.getCorreoElectronico().equals(clienteViejo.getCorreoElectronico()));

    if (!hasSameEmail && esMailRepetido(cliente.getCorreoElectronico())) {
      throw new ClienteMailDuplicadoException(cliente.getCorreoElectronico());
    }

    if (!areUsuariosValid(cliente.getUsuarios())) {
      throw new ClienteUsuariosInvalidException(cliente.getUsuarios());
    }

    clienteViejo.setCorreoElectronico(cliente.getCorreoElectronico());
    clienteViejo.setHabilitadoOnline(cliente.getHabilitadoOnline());
    clienteViejo.setMaximoCuentaCorriente(cliente.getMaximoCuentaCorriente());
    clienteViejo.setRazonSocial(cliente.getRazonSocial());
    clienteViejo.setCuit(cliente.getCuit());
    clienteViejo.setUsuarios(cliente.getUsuarios());

    return clienteRepo.save(clienteViejo);
  }

  @Override
  public void deleteCliente(Integer id) throws ClienteNoEncontradoException {
    if (!clienteRepo.existsById(id)) {
      throw new ClienteNoEncontradoException(id);
    }

    clienteRepo.deleteById(id);
  }

  // Filtra entre todos los clientes alguno que tenga mismo mail que el pasado por
  // parámetro
  private boolean esMailRepetido(String mail) {
    return clienteRepo.findAll().stream().filter(c -> c.getCorreoElectronico().equals(mail)).findAny().isPresent();
  }

  private boolean areUsuariosValid(List<Usuario> usuarios) {

    if (usuarios == null)
      return true;

    Integer cantidadGerentes = 0;

    for (Usuario u : usuarios) {

      if (u.getId() == null)
        continue;

      Optional<Usuario> savedUsuario = usuarioRepo.findById(u.getId());

      if (savedUsuario.isEmpty())
        continue;

      TipoUsuario tipo = savedUsuario.get().getTipoUsuario();

      if (tipo == null)
        continue;

      if ("GERENTE".equalsIgnoreCase(tipo.getTipo()))
        cantidadGerentes++;

      if (cantidadGerentes > 1)
        return false;

    }

    return true;

  }

}
