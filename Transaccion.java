import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private LocalDateTime fecha;
    private int tipoDeTransaccion; // 0 extraccion 1 deposito CAMBIE DE BOOLEAN A INT PARA FACILITAR LECTURA DE ARCHIVO
    private Double monto;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    Transaccion(int tipoDeTransaccion, Double monto){
        this.fecha = LocalDateTime.now();
        this.tipoDeTransaccion = tipoDeTransaccion;
        this.monto = monto;
    }
    
    Transaccion(String fecha, int tipoDeTransaccion, Double monto){
        this.fecha = LocalDateTime.parse(fecha, formatter);
        this.tipoDeTransaccion = tipoDeTransaccion;
        this.monto = monto;
    }

    public String toString(){
        return fecha.format(formatter) + "," + tipoDeTransaccion + "," + monto;
    }
}