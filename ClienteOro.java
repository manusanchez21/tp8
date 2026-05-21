import java.util.ArrayList;

/**
 * Cliente de nivel Oro con tarjeta de crédito asociada.
 */
public class ClienteOro extends Cliente {
    private TarjetaDeCredito credix;

    /**
     * Crea un cliente Oro nuevo con tarjeta por defecto.
     */
    public ClienteOro(Integer dni, String nombre){
        super(dni, nombre);
        this.credix = new TarjetaDeCredito(250000);
    }

    /**
     * Constructor usado al cargar desde archivo.
     */
    public ClienteOro(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) {
        super(dni, nombre, estado, anioIngreso, saldoPesos, saldoDolares, numeroDeCuentaDolares, numeroDeCuentaPesos, transaccionesDolares, transaccionesPesos);
        this.credix = new TarjetaDeCredito(250000);
    }
    
    /**
     * @return límite de la tarjeta asociada
     */
    public double getLimiteTarjeta() {
        return credix.getLimite();
    }
    
    @Override
    public String getCodigoNivel(){
        return "2"; 
    }
}