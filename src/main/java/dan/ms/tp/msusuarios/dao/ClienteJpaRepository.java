package dan.ms.tp.msusuarios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente,Integer> {
    
    public Cliente findByCuit(String cuit);
}
