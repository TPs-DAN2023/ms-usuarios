package dan.ms.tp.msusuarios.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dan.ms.tp.msusuarios.dao.ClienteJpaRepository;
import dan.ms.tp.msusuarios.dao.TipoUsuarioJpaRepository;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.ClienteNoEncontradoException;
import dan.ms.tp.msusuarios.exception.TipoUsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.exception.UsuarioPasswordInvalidException;
import dan.ms.tp.msusuarios.exception.UsuarioUsernameDuplicadoException;
import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.TipoUsuario;
import dan.ms.tp.msusuarios.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

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

    if (u.isEmpty())
      throw new UsuarioNoEncontradoException(id);

    return u.get();
  }

  @Override
  public List<Usuario> getUsuariosByCliente(Integer idCliente) throws ClienteNoEncontradoException {

    Optional<Cliente> cliente = clienteRepo.findById(idCliente);

    if (cliente.isEmpty())
      throw new ClienteNoEncontradoException(idCliente);

    return cliente.get().getUsuarios();
  }

  @Override
  public Usuario createUsuario(Usuario usuario)
      throws UsuarioUsernameDuplicadoException, ClienteNoEncontradoException, UsuarioPasswordInvalidException {

    if (esUserNameRepetido(usuario.getUserName())) {
      throw new UsuarioUsernameDuplicadoException(usuario.getUserName());
    }

    if (!isPasswordValid(usuario.getPassword())) {
      throw new UsuarioPasswordInvalidException(usuario.getPassword());
    }

    return usuarioRepo.save(usuario);
  }

  @Override
  public Usuario updateUsuario(Usuario usuario, Integer id)
      throws UsuarioUsernameDuplicadoException, UsuarioNoEncontradoException, ClienteNoEncontradoException,
      UsuarioUsernameDuplicadoException, UsuarioPasswordInvalidException {

    Optional<Usuario> usuarioViejo = usuarioRepo.findById(id);

    if (usuarioViejo.isEmpty()) {
      throw new UsuarioNoEncontradoException(id);
    }

    Usuario u = usuarioViejo.get();

    Boolean hasSameUserName = (usuario.getUserName() != null && usuario.getUserName().equals(u.getUserName()));

    if (!hasSameUserName && esUserNameRepetido(usuario.getUserName())) {
      throw new UsuarioUsernameDuplicadoException(usuario.getUserName());
    }

    if (!isPasswordValid(usuario.getPassword())) {
      throw new UsuarioPasswordInvalidException(usuario.getPassword());
    }

    u.setCorreoElectronico(usuario.getCorreoElectronico());
    u.setPassword(usuario.getPassword());
    u.setTipoUsuario(usuario.getTipoUsuario());
    u.setUserName(usuario.getUserName());

    return usuarioRepo.save(u);
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

    if (cliente.isEmpty())
      throw new ClienteNoEncontradoException(idCliente);

    Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepo.findById(tipoUsuarioId);

    if (tipoUsuario.isEmpty())
      throw new TipoUsuarioNoEncontradoException(tipoUsuarioId);

    List<Usuario> resultado = cliente.get().getUsuarios().stream()
        .filter((u) -> u.getTipoUsuario().equals(tipoUsuario.get())).toList();

    return resultado;
  }

  // Filtra entre todos los usuarios alguno que tenga mismo username que el pasado
  // por parámetro
  private boolean esUserNameRepetido(String username) {
    return usuarioRepo.findAll().stream().filter(u -> u.getUserName().equals(username)).findAny().isPresent();
  }

  private boolean isPasswordValid(String password) {

    if (password == null)
      return false;

    if (password.length() < 12)
      return false;

    Pattern searchPattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    Boolean containsSpecialCharacter = searchPattern.matcher(password).find();

    if (!containsSpecialCharacter)
      return false;

    searchPattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);

    Boolean containsNumber = searchPattern.matcher(password).find();

    if (!containsNumber)
      return false;

    searchPattern = Pattern.compile("[a-z]");

    Boolean containsLowerCase = searchPattern.matcher(password).find();

    if (!containsLowerCase)
      return false;

    searchPattern = Pattern.compile("[A-Z]");

    Boolean containsUpperCase = searchPattern.matcher(password).find();

    if (!containsUpperCase)
      return false;

    return true;

  }
}
