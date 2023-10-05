package dan.ms.tp.msusuarios.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dan.ms.tp.msusuarios.modelo.Usuario;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario,Integer>{
    
    public Usuario findByIdCliente(Integer idCliente);
    public List<Usuario> findAllByTipoUsuario(String tipoUsuario);
}
