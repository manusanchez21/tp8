/**
 * Excepción lanzada cuando una extracción supera el saldo disponible.
 *
 * @author Jano Chiambretto
 * @author Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class SaldoInsuficienteExeption extends Exception {
    /**
     * Crea la excepción con un mensaje descriptivo.
     * @param errorMessage mensaje de error
     */
    public SaldoInsuficienteExeption(String errorMessage){
        super(errorMessage);
    }
}
