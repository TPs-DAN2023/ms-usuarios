package dan.ms.tp.msusuarios.exception;

import java.util.List;

import dan.ms.tp.msusuarios.modelo.Usuario;

public class ClienteUsuariosInvalidException extends Exception {
    
    public ClienteUsuariosInvalidException(List<Usuario> usuarios){
        super("El cliente xolo puede tener asociado un usuario de tipo gerente.");
      }

}
