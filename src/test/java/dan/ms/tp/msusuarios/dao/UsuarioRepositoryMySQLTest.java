package dan.ms.tp.msusuarios.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import dan.ms.tp.msusuarios.modelo.Usuario;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
public class UsuarioRepositoryMySQLTest {

  @Container
  public static MySQLContainer container = new MySQLContainer("mysql:8.0.26")
      .withDatabaseName("dan")
      .withUsername("danuser")
      .withPassword("danpassword");

  @DynamicPropertySource
  public static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.username", container::getUsername);
    registry.add("spring.datasource.password", container::getPassword);
  }

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