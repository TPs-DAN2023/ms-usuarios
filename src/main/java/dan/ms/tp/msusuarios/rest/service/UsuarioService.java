package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import dan.ms.tp.msusuarios.modelo.Usuario;

public interface UsuarioService {
  List<Usuario> getAllUsuarios();
  Usuario getUsuarioById(Integer id);
  Usuario getUsuarioByCliente(Integer idCliente);
  Usuario saveOrUpdateUsuario(Usuario nuevoUsuario, Integer id);
  void deleteUsuario(Integer id);
}
