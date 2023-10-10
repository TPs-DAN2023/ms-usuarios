package dan.ms.tp.msusuarios.exception;

public class UsuarioNoAsociadoException extends Exception{
  public UsuarioNoAsociadoException(Integer idCliente){
    super("El cliente id:"+idCliente.toString()+" no posee un usuario asociado");
  }
}
