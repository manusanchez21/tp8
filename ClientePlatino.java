import java.util.ArrayList;

public class ClientePlatino extends Cliente {
    private TarjetaDeCredito premium;

    public ClientePlatino(Integer dni, String nombre){
        super(dni, nombre);
        this.premium = new TarjetaDeCredito(500000);
    }

    public ClientePlatino(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) {
        super(dni, nombre, estado, anioIngreso, saldoPesos, saldoDolares, numeroDeCuentaDolares, numeroDeCuentaPesos, transaccionesDolares, transaccionesPesos);
        this.premium = new TarjetaDeCredito(500000);
    }
    
    public double getLimiteTarjeta() {
        return premium.getLimite();
    }
    
    @Override
    public String getCodigoNivel(){
        return "3"; 
    }
}