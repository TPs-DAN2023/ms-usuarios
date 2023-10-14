package dan.ms.tp.msusuarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USR_TIPO_USUARIO")
@Data
public class TipoUsuario {

    @Id
    private Integer id;
    private String tipo;

    
}