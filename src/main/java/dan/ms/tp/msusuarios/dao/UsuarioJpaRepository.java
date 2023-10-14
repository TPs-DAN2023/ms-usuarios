package dan.ms.tp.msusuarios.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dan.ms.tp.msusuarios.modelo.Usuario;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario,Integer>{
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
    // public Usuario findUsuarioByIdCliente(Integer idCliente);
    // public List<Usuario> findDistinctUsuarioByTipoUsuarioOrderByUserNameDesc(String tipoUsuario);
}
