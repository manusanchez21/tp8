/**
 * Excepción lanzada cuando no se encuentra un cliente solicitado.
 */
public class ClienteNoEncontradoException extends Exception {
    /**
     * Crea la excepción con un mensaje descriptivo.
     * 
     * @param errorMessage mensaje de error
     */
    public ClienteNoEncontradoException(String errorMessage){
        super(errorMessage);
    }
}
