package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioDuplicadoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoAsociadoException;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{
  
  @Autowired
  UsuarioJpaRepository usuarioRepo;
  @Autowired
  ClienteJpaRepository clienteRepo;

  @Override
  public List<Usuario> getAllUsuarios() {
    return usuarioRepo.findAll();
  }

  @Override
  public Usuario getUsuarioById(Integer id) throws UsuarioNoEncontradoException {
    Optional<Usuario> u = usuarioRepo.findById(id);

    if(!u.isPresent())
      throw new UsuarioNoEncontradoException(id);

    return u.get();
  }

  @Override
  public Usuario getUsuarioByCliente(Integer idCliente) throws 
    UsuarioNoAsociadoException, ClienteNoEncontradoException {

    if(!clienteRepo.findById(idCliente).isPresent())
      throw new ClienteNoEncontradoException(idCliente);

    //Usuario u = usuarioRepo.findByIdCliente(idCliente);
    Usuario u = null;
    if(u == null) 
      throw new UsuarioNoAsociadoException(idCliente);

    return u;
  }

  @Override
  public Usuario addOrUpdateUsuario(Usuario nuevoUsuario, Integer id) throws UsuarioDuplicadoException, UsuarioNoEncontradoException {
    
    if (id == null){
      if(usuarioRepo.existsById(nuevoUsuario.getId()))
        throw new UsuarioDuplicadoException(nuevoUsuario.getId());

      return usuarioRepo.save(nuevoUsuario);
    }

    // se podra hacer asi?
    if(!usuarioRepo.existsById(id))
      throw new UsuarioNoEncontradoException(id);

    return usuarioRepo.save(nuevoUsuario);

  }

  @Override
  public void deleteUsuario(Integer id) throws UsuarioNoEncontradoException {
    if (!usuarioRepo.existsById(id))
      throw new UsuarioNoEncontradoException(id);

      usuarioRepo.deleteById(id);
  }

  
}
