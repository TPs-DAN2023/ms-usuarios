package dan.ms.tp.msusuarios.exception;

public class ClienteDuplicadoException extends Exception{
  public ClienteDuplicadoException(Integer id){
    super("El cliente con id: "+id.toString()+" ya existe.");
  }
  
}
