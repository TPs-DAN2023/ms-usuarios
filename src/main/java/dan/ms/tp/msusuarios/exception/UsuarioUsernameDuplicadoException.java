package dan.ms.tp.msusuarios.exception;

public class UsuarioUsernameDuplicadoException extends Exception {
  public UsuarioUsernameDuplicadoException(String username){
    super("Ya existe otro usuario con el username: "+username+" asignado.");
  }
}
