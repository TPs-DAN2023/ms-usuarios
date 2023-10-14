package dan.ms.tp.msusuarios.exception;

public class TipoUsuarioNoEncontradoException extends Exception{
  public TipoUsuarioNoEncontradoException(Integer id){
    super("El tipo de usuario con id: "+id.toString()+" no existe.");
  }
}
