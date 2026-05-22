/**
 * Excepción lanzada cuando no se encuentra un cliente solicitado.
 *
 * @author Jano Chiambretto
 * @author Manuel Sanchez Fossa
 * @version 1.0.0
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
