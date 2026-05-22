import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una transacción bancaria (depósito o extracción) en una cuenta.
 * Contiene la fecha, el tipo de moneda, tipo de transacción y el monto.
 *
 * @author Jano Chiambretto
 * @author Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class Transaccion {
    private LocalDateTime fecha;
    private int tipoDeMoneda; // 0 dolares 1 pesos
    private int tipoDeTransaccion; // 0 extraccion 1 deposito CAMBIE DE BOOLEAN A INT PARA FACILITAR LECTURA DE ARCHIVO
    private Double monto;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Crea una transacción con fecha actual.
     * @param tipoDeMoneda 0=dólares, 1=pesos
     * @param tipoDeTransaccion 0=extracción, 1=depósito
     * @param monto importe de la operación
     */
    Transaccion(int tipoDeMoneda, int tipoDeTransaccion, Double monto){
        this.fecha = LocalDateTime.now();
        this.tipoDeMoneda = tipoDeMoneda;
        this.tipoDeTransaccion = tipoDeTransaccion;
        this.monto = monto;
    }
    
    /**
     * Crea una transacción a partir de una cadena de fecha (usado al leer archivos).
     * @param fecha fecha en formato "dd-MM-yyyy HH:mm"
     * @param tipoDeMoneda 0=dólares, 1=pesos
     * @param tipoDeTransaccion 0=extracción, 1=depósito
     * @param monto importe de la operación
     */
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

    /**
     * Formato de texto usado al persistir transacciones.
     */
    public String toString(){
        return fecha.format(formatter) + "," + tipoDeTransaccion + "," + monto;
    }
}