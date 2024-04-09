package dan.ms.tp.msusuarios.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {
  // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
  public Cliente findClienteByCuit(String cuit);

  @Query("SELECT c FROM Cliente c JOIN c.usuarios u WHERE u.id = :userId")
  Optional<Cliente> findClienteByUsuarioId(@Param("userId") Integer userId);

}
