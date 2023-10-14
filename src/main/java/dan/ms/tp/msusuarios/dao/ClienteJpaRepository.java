package dan.ms.tp.msusuarios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dan.ms.tp.msusuarios.modelo.Cliente;

public interface ClienteJpaRepository extends JpaRepository<Cliente,Integer> {
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
    public Cliente findClienteByCuit(String cuit);
}
