package dan.ms.tp.msusuarios.rest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.exception.UsuarioNoEncontradoException;
import dan.ms.tp.msusuarios.modelo.Usuario;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

  @Mock
  private UsuarioJpaRepository usuarioRepository;

  @InjectMocks
  private UsuarioServiceImpl usuarioService;

  @Test
  public void testGetUsuario() {
    Usuario user = new Usuario();
    user.setId(1);
    user.setUserName("user1");

    when(usuarioRepository.findById(1)).thenReturn(Optional.of(user));

    Usuario result = null;
    try {
      result = usuarioService.getUsuario(1);
    } catch (UsuarioNoEncontradoException e) {
      System.out.println(e.getMessage());
    }

    assertEquals(user, result);
  }
}