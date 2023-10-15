package dan.ms.tp.msusuarios.rest.service;

import java.util.List;

import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.TipoUsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioUsernameDuplicadoException;
import dan.ms.tp.msusuarios.modelo.Usuario;

public interface UsuarioService {
  List<Usuario> getAllUsuarios();
  Usuario getUsuario(Integer id) throws UsuarioNoEncontradoException;
  List<Usuario> getUsuariosByCliente(Integer idCliente) throws ClienteNoEncontradoException;
  List<Usuario> getUsuariosOfTipoUsuarioByCliente(Integer idTipoUsuario, Integer idCliente) throws ClienteNoEncontradoException, TipoUsuarioNoEncontradoException;
  Usuario createUsuario(Usuario usuario) throws UsuarioUsernameDuplicadoException,
  ClienteNoEncontradoException;
  Usuario updateUsuario(Usuario usuario, Integer id) throws UsuarioUsernameDuplicadoException, 
  UsuarioNoEncontradoException, ClienteNoEncontradoException;
  void deleteUsuario(Integer id) throws UsuarioNoEncontradoException;
}
