package dan.ms.tp.msusuarios.exception;

public class UsuarioNoEncontradoException extends Exception{

  public UsuarioNoEncontradoException(Integer id) {
    super("El usuario con id: "+id.toString()+" no existe.");
  }

}
