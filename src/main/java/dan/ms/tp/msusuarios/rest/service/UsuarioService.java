package dan.ms.tp.msusuarios.rest.service;

import java.util.List;

import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoAsociadoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioUsernameDuplicadoException;
import dan.ms.tp.msusuarios.modelo.Usuario;

public interface UsuarioService {
  List<Usuario> getAllUsuarios();
  Usuario getUsuario(Integer id) throws UsuarioNoEncontradoException;
  Usuario getUsuarioByCliente(Integer idCliente) throws UsuarioNoAsociadoException, 
  ClienteNoEncontradoException;
  Usuario createUsuario(Usuario usuario) throws UsuarioUsernameDuplicadoException,
  ClienteNoEncontradoException;
  void updateUsuario(Usuario usuario, Integer id) throws UsuarioUsernameDuplicadoException, 
  UsuarioNoEncontradoException, ClienteNoEncontradoException;
  void deleteUsuario(Integer id) throws UsuarioNoEncontradoException;
}
