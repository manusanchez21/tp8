import java.util.ArrayList;

/**
 * Contenedor auxiliar que agrupa transacciones por moneda y mantiene saldos.
 *
 * @author Jano Chiambretto
 * @author Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class ArrayListTransacciones {
    private ArrayList<Transaccion> transaccionesPesos = new ArrayList<>();
    private ArrayList<Transaccion> transaccionesDolares = new ArrayList<>();
    private double saldoDolares;
    private double saldoPesos;

    /**
     * Crea un contenedor vacío para transacciones.
     */
    public ArrayListTransacciones() {
        this.transaccionesPesos = new ArrayList<Transaccion>();
        this.transaccionesDolares = new ArrayList<Transaccion>();
    }

    /**
     * Agrega una transacción en pesos.
     */
    public void agregarTransaccionPesos(Transaccion t) {
        transaccionesPesos.add(t);
    }

    /**
     * Agrega una transacción en dólares.
     */
    public void agregarTransaccionDolares(Transaccion t) {
        transaccionesDolares.add(t);
    }

    /**
     * @return transacciones en dólares
     */
    public ArrayList<Transaccion> getTransaccionesDolares() {
        return transaccionesDolares;
    }

    /**
     * @return transacciones en pesos
     */
    public ArrayList<Transaccion> getTransaccionesPesos() {
        return transaccionesPesos;
    }

    /**
     * Establece el saldo en dólares calculado al leer archivos.
     */
    public void setSaldoDolares(double saldoDolares) {
        this.saldoDolares = saldoDolares;
    }

    /**
     * Establece el saldo en pesos calculado al leer archivos.
     */
    public void setSaldoPesos(double saldoPesos) {
        this.saldoPesos = saldoPesos;
    }

    /**
     * @return saldo en dólares
     */
    public double getSaldoDolares() {
        return saldoDolares;
    }
    
    /**
     * @return saldo en pesos
     */
    public double getSaldoPesos() {
        return saldoPesos;
    }
}