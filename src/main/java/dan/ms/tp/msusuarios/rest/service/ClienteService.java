package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteService {
  List<Cliente> getAllClientes();
  Cliente getCliente(Integer id);
  Cliente getCliente(String cuit);
  Cliente addOrUpdateCliente(Cliente cliente, Integer id);
  void deleteCliente(Integer id);
}
