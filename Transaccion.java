import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private LocalDateTime fecha;
    private Integer tipoDeTransaccion; // 0 extraccion 1 deposito
    private Double monto;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    Transaccion(Integer tipoDeTransaccion, Double monto){
        this.fecha = LocalDateTime.now();
        this.tipoDeTransaccion = tipoDeTransaccion;
        this.monto = monto;
    }
    
    Transaccion(String fecha, Integer tipoDeTransaccion, Double monto){
        this.fecha = LocalDateTime.parse(fecha, formatter);
        this.tipoDeTransaccion = tipoDeTransaccion;
        this.monto = monto;
    }

    public String toString(){
        return fecha.format(formatter) + "," + tipoDeTransaccion + "," + monto;
    }
}