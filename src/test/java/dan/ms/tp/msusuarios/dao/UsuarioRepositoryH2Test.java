package dan.ms.tp.msusuarios.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import dan.ms.tp.msusuarios.modelo.Usuario;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UsuarioRepositoryH2Test {

  @Autowired
  private UsuarioJpaRepository usuarioRepository;

  @Test
  public void testFindByUserName() {
    Usuario user = new Usuario();
    user.setUserName("user1");
    usuarioRepository.save(user);

    Usuario foundUser = usuarioRepository.findByUserName("user1");
    assertThat(foundUser).isNotNull();
    assertThat(foundUser.getUserName()).isEqualTo("user1");
  }
}