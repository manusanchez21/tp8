import java.util.ArrayList;

/**
 * Cliente de nivel Platino con tarjeta premium asociada.
 */
public class ClientePlatino extends Cliente {
    private TarjetaDeCredito premium;

    /**
     * Crea un cliente Platino nuevo con tarjeta premium.
     */
    public ClientePlatino(Integer dni, String nombre){
        super(dni, nombre);
        this.premium = new TarjetaDeCredito(500000);
    }

    /**
     * Constructor usado al cargar desde archivo.
     */
    public ClientePlatino(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) {
        super(dni, nombre, estado, anioIngreso, saldoPesos, saldoDolares, numeroDeCuentaDolares, numeroDeCuentaPesos, transaccionesDolares, transaccionesPesos);
        this.premium = new TarjetaDeCredito(500000);
    }
    
    /**
     * @return límite de la tarjeta premium
     */
    public double getLimiteTarjeta() {
        return premium.getLimite();
    }
    
    @Override
    public String getCodigoNivel(){
        return "3"; 
    }
}