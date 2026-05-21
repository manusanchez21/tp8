import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private LocalDateTime fecha;
    private int tipoDeMoneda; // 0 dolares 1 pesos
    private int tipoDeTransaccion; // 0 extraccion 1 deposito CAMBIE DE BOOLEAN A INT PARA FACILITAR LECTURA DE ARCHIVO
    private Double monto;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    Transaccion(int tipoDeMoneda, int tipoDeTransaccion, Double monto){
        this.fecha = LocalDateTime.now();
        this.tipoDeMoneda = tipoDeMoneda;
        this.tipoDeTransaccion = tipoDeTransaccion;
        this.monto = monto;
    }
    
    Transaccion(String fecha, int tipoDeMoneda, int tipoDeTransaccion, Double monto){
        this.fecha = LocalDateTime.parse(fecha, formatter);
        this.tipoDeMoneda = tipoDeMoneda;
        this.tipoDeTransaccion = tipoDeTransaccion;
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getTipoDeMoneda() {
        return tipoDeMoneda;
    }

    public int getTipoDeTransaccion() {
        return tipoDeTransaccion;
    }

    public Double getMonto() {
        return monto;
    }

    public String getMonedaTexto() {
        return tipoDeMoneda == 0 ? "Dólares" : "Pesos";
    }

    public String getTipoTransaccionTexto() {
        return tipoDeTransaccion == 0 ? "Extracción" : "Depósito";
    }

    public String toString(){
        return fecha.format(formatter) + "," + tipoDeTransaccion + "," + monto;
    }
}