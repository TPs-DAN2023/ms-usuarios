package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.TipoUsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;

@Service
public class TipoUsuarioImpl implements TipoUsuarioService {

  @Autowired
  TipoUsuarioJpaRepository tipoUsuarioRepo;

  @Override
  public List<TipoUsuario> getAllTipoUsuario() {
    return tipoUsuarioRepo.findAll();
  }

  @Override
  public TipoUsuario getTipoUsuario(Integer id) throws TipoUsuarioNoEncontradoException {
    Optional<TipoUsuario> t = tipoUsuarioRepo.findById(id);

    if (t.isEmpty()) {
      throw new TipoUsuarioNoEncontradoException(id);
    }

    return t.get();
  }

}
