package dan.ms.tp.msusuarios.rest;

import dan.ms.tp.msusuarios.modelo.Cliente;
import dan.ms.tp.msusuarios.modelo.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UsuarioWithToken extends Usuario {

  private String token;
  private Cliente cliente;

  public UsuarioWithToken(Usuario u) {
    super();
    this.setUserName(u.getUserName());
    this.setCorreoElectronico(this.getCorreoElectronico());
    this.setPassword(this.getPassword());
    this.setId(u.getId());
    this.setTipoUsuario(u.getTipoUsuario());
  }

}
