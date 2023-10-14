package dan.ms.tp.msusuarios.exception;

public class ClienteNoEncontradoException extends Exception{
  
  public ClienteNoEncontradoException(Integer id){
    super("El cliente con id: "+id.toString()+" no existe.");
  }  
  public ClienteNoEncontradoException(String cuit){
    super("El cliente con cuit: "+cuit+" no existe.");
  }  
}
