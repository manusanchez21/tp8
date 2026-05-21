import java.util.ArrayList;

public class ArrayListTransacciones {
    private ArrayList<Transaccion> transaccionesPesos = new ArrayList<>();
    private ArrayList<Transaccion> transaccionesDolares = new ArrayList<>();
    private double saldoDolares;
    private double saldoPesos;

    public ArrayListTransacciones() {
        this.transaccionesPesos = new ArrayList<Transaccion>();
        this.transaccionesDolares = new ArrayList<Transaccion>();
    }

    public void agregarTransaccionPesos(Transaccion t) {
        transaccionesPesos.add(t);
    }

    public void agregarTransaccionDolares(Transaccion t) {
        transaccionesDolares.add(t);
    }

    public ArrayList<Transaccion> getTransaccionesDolares() {
        return transaccionesDolares;
    }

    public ArrayList<Transaccion> getTransaccionesPesos() {
        return transaccionesPesos;
    }

    public void setSaldoDolares(double saldoDolares) {
        this.saldoDolares = saldoDolares;
    }

    public void setSaldoPesos(double saldoPesos) {
        this.saldoPesos = saldoPesos;
    }

    public double getSaldoDolares() {
        return saldoDolares;
    }
    
    public double getSaldoPesos() {
        return saldoPesos;
    }
}