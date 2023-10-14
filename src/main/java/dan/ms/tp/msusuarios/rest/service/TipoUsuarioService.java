package dan.ms.tp.msusuarios.rest.service;

import java.util.List;

import dan.ms.tp.msusuarios.exception.TipoUsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;

public interface TipoUsuarioService {
  List<TipoUsuario> getAllTipoUsuario();
  TipoUsuario getTipoUsuario(Integer id) throws TipoUsuarioNoEncontradoException;
}
