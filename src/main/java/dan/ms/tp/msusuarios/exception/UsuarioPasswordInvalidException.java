package dan.ms.tp.msusuarios.exception;

public class UsuarioPasswordInvalidException extends Exception{
  public UsuarioPasswordInvalidException(String password){
    super("La contrasena " + password + " del usuario no tiene el formato correcto.");
  }
  
}