package dan.ms.tp.msusuarios.exception;

public class ClienteMailDuplicadoException extends Exception{
  public ClienteMailDuplicadoException(String mail){
    super("Ya existe otro cliente con el mail: "+mail+" asignado.");
  }
}
