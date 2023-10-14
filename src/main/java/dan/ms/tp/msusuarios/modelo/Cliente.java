package dan.ms.tp.msusuarios.modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USR_CLIENTES")
@Data
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    
    private String cuit;

    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;
    
    @Column(name = "MAX_CTA_CTE")
    private Double maximoCuentaCorriente;

    @Column(name = "HABILITADO_ONLINE")
    private Boolean habilitadoOnline;

    @OneToMany
    @JoinColumn(name = "ID_CLIENTE")
    private List<Usuario> usuarios;

    
}
