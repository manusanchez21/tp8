import java.util.ArrayList;

/**
 * Cliente de nivel Plata. Extiende `Cliente` y proporciona el código de nivel
 * correspondiente.
 *
 * @author Jano Chiambretto
 * @author Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class ClientePlata extends Cliente {

    /**
     * Crea un cliente Plata nuevo.
     */
    public ClientePlata(Integer dni, String nombre){
        super(dni, nombre);
    }
    
    /**
     * Constructor usado al cargar desde archivo.
     */
    public ClientePlata(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) {
        super(dni, nombre, estado, anioIngreso, saldoPesos, saldoDolares, numeroDeCuentaDolares, numeroDeCuentaPesos, transaccionesDolares, transaccionesPesos);
    }


    @Override
    public String getCodigoNivel(){
        return "1"; 
    }
}