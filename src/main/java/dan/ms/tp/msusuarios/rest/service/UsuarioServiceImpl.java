package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.TipoUsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioUsernameDuplicadoException;
import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService{
  
  @Autowired
  UsuarioJpaRepository usuarioRepo;
  @Autowired
  ClienteJpaRepository clienteRepo;
  @Autowired
  TipoUsuarioJpaRepository tipoUsuarioRepo;

  @Override
  public List<Usuario> getAllUsuarios() {
    return usuarioRepo.findAll();
  }

  @Override
  public Usuario getUsuario(Integer id) throws UsuarioNoEncontradoException {
    Optional<Usuario> u = usuarioRepo.findById(id);

    if(!u.isPresent())
      throw new UsuarioNoEncontradoException(id);

    return u.get();
  }

  @Override
  public List<Usuario> getUsuariosByCliente(Integer idCliente) throws 
    ClienteNoEncontradoException {

    Optional<Cliente> cliente = clienteRepo.findById(idCliente);

    if(!cliente.isPresent())
      throw new ClienteNoEncontradoException(idCliente);

      return cliente.get().getUsuarios();
  }

    @Override
  public Usuario createUsuario(Usuario usuario) throws UsuarioUsernameDuplicadoException, ClienteNoEncontradoException {
  
    return usuarioRepo.save(usuario);  
  }

  @Override
  public void updateUsuario(Usuario usuario, Integer id)
      throws UsuarioUsernameDuplicadoException, UsuarioNoEncontradoException, ClienteNoEncontradoException {
   
        Optional<Usuario> usuarioViejo = usuarioRepo.findById(id);

        if (!usuarioViejo.isPresent()) throw new UsuarioNoEncontradoException(id);

        Usuario u = usuarioViejo.get();

        u.setCorreoElectronico(usuario.getCorreoElectronico());
        u.setPassword(usuario.getPassword());
        u.setTipoUsuario(usuario.getTipoUsuario());
        u.setUserName(usuario.getUserName());

       usuarioRepo.save(u);

  }
  
  @Override
  public void deleteUsuario(Integer id) throws UsuarioNoEncontradoException {
    if (!usuarioRepo.existsById(id))
      throw new UsuarioNoEncontradoException(id);

      usuarioRepo.deleteById(id);
  }

  @Override
  public List<Usuario> getUsuariosOfTipoUsuarioByCliente(Integer tipoUsuarioId, Integer idCliente)
      throws ClienteNoEncontradoException, TipoUsuarioNoEncontradoException {
   
        Optional<Cliente> cliente = clienteRepo.findById(idCliente);

        if(!cliente.isPresent()) throw new ClienteNoEncontradoException(idCliente);

        Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepo.findById(tipoUsuarioId);
        
        if(!tipoUsuario.isPresent()) throw new TipoUsuarioNoEncontradoException(tipoUsuarioId);

        List<Usuario> resultado = cliente.get().getUsuarios().stream().filter((u) -> u.getTipoUsuario().equals(tipoUsuario.get())).toList(); 

        return resultado;
  }
  
}
