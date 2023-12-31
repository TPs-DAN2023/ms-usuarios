package dan.ms.tp.msusuarios.rest.service;

import java.util.List;

import dan.ms.tp.msusuarios.exception.ClienteMailDuplicadoException;
import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.ClienteUsuariosInvalidException;
import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteService {
  List<Cliente> getAllClientes();
  Cliente getCliente(Integer id) throws ClienteNoEncontradoException;
  Cliente getCliente(String cuit) throws ClienteNoEncontradoException;
  Cliente createCliente(Cliente cliente) throws ClienteMailDuplicadoException, ClienteUsuariosInvalidException;
  Cliente updateCliente(Cliente cliente, Integer id) throws ClienteNoEncontradoException, ClienteUsuariosInvalidException, 
  ClienteMailDuplicadoException;
  void deleteCliente(Integer id) throws ClienteNoEncontradoException;
}
