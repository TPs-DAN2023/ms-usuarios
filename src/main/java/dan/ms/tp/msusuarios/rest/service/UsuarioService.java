package dan.ms.tp.msusuarios.rest.service;

import java.util.List;

import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioDuplicadoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoAsociadoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.modelo.Usuario;

public interface UsuarioService {
  List<Usuario> getAllUsuarios();
  Usuario getUsuarioById(Integer id) throws UsuarioNoEncontradoException;
  Usuario getUsuarioByCliente(Integer idCliente) throws UsuarioNoAsociadoException, ClienteNoEncontradoException;
  Usuario addOrUpdateUsuario(Usuario nuevoUsuario, Integer id) throws UsuarioDuplicadoException, UsuarioNoEncontradoException;
  void deleteUsuario(Integer id) throws UsuarioNoEncontradoException;
}
