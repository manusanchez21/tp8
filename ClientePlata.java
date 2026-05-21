import java.util.ArrayList;

public class ClientePlata extends Cliente {

    public ClientePlata(Integer dni, String nombre){
        super(dni, nombre);
    }
    
    public ClientePlata(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) {
        super(dni, nombre, estado, anioIngreso, saldoPesos, saldoDolares, numeroDeCuentaDolares, numeroDeCuentaPesos, transaccionesDolares, transaccionesPesos);
    }


    @Override
    public String getCodigoNivel(){
        return "1"; 
    }
}