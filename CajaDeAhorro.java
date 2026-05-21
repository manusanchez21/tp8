import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CajaDeAhorro {
    private static int numeroCuentaCounter = 0; // aca hay un error de la primera vexz q leemos el archivo
    private Integer numeroCuenta;
    private Double saldo;
    private ArrayList<Transaccion> transacciones;
    private Integer tipoDeMoneda; // 0 dolares 1 pesos argentinos

    CajaDeAhorro(Integer tipoDeMoneda){
        this.saldo = 0.0;
        this.numeroCuenta = numeroCuentaCounter;
        numeroCuentaCounter++;
        this.transacciones = new ArrayList<Transaccion>();
        this.tipoDeMoneda = tipoDeMoneda;
    }
    CajaDeAhorro(Integer numeroCuenta, Double saldo, Integer tipoDeMoneda, ArrayList<Transaccion> transacciones){
        this.numeroCuenta = numeroCuenta;
        if (numeroCuenta >= numeroCuentaCounter) {
            numeroCuentaCounter = numeroCuenta + 1;
        }
        this.saldo = saldo;
        this.transacciones = transacciones;
        this.tipoDeMoneda = tipoDeMoneda;
    }

    public void extraccion(Double monto) throws SaldoInsuficienteExeption, IOException{
        if (monto > saldo) {
            throw new SaldoInsuficienteExeption("El monto pedido por la extraccion es mayor al saldo de la caja de ahorro, no se realiza ningun cambio");
        }else{
            Transaccion t = new Transaccion(tipoDeMoneda, 0, monto);
            transacciones.add(t);
            saldo -= monto;
            guardarTransaccion(t);
        }
    }
    
    public void deposito(Double monto) throws IOException{
        Transaccion t = new Transaccion(tipoDeMoneda, 1, monto);
        transacciones.add(t);
        saldo += monto;
        guardarTransaccion(t);
    }

    private void guardarTransaccion(Transaccion t) throws IOException{
        try (FileWriter writer = new FileWriter("transacciones.txt", true);){ // esto es try-with-resources, hace que writer.close se ejecute siempre
            writer.append(numeroCuenta + "," + tipoDeMoneda + "," + t.toString() + "\n");
        } catch (IOException e) {
            throw new IOException("Error al guardar el deposito en el archivo", e);
        }
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public Integer getTipoDeMoneda() {
        return tipoDeMoneda;
    }

    public static void setNumeroCuentaCounter(int numeroCuentaCounter) {
        CajaDeAhorro.numeroCuentaCounter = numeroCuentaCounter;
    }
}