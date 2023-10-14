package dan.ms.tp.msusuarios.exception;

public class UsuarioDuplicadoException extends Exception{
  public UsuarioDuplicadoException(Integer id){
    super("El usuario con id: "+id.toString()+" ya existe.");
  }
}
