import java.util.ArrayList;

public class ClienteOro extends Cliente {
    private TarjetaDeCredito credix;

    public ClienteOro(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) {
        super(dni, nombre, estado, anioIngreso, saldoPesos, saldoDolares, numeroDeCuentaDolares, numeroDeCuentaPesos, transaccionesDolares, transaccionesPesos);
        this.credix = new TarjetaDeCredito(250000);
    }
    public double getLimiteTarjeta() {
        return credix.getLimite();
    }
    
    @Override
    public String getCodigoNivel(){
        return "2"; 
    }
}