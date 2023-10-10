package dan.ms.tp.msusuarios.rest.service;

import java.util.List;

import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteService {
  List<Cliente> getAllClientes();
  Cliente getCliente(Integer id) throws ClienteNoEncontradoException;
  Cliente getCliente(String cuit) throws ClienteNoEncontradoException;
  Cliente addOrUpdateCliente(Cliente cliente, Integer id);
  void deleteCliente(Integer id);
}
