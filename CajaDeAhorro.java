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
        this.numeroCuenta = numeroCuentaCounter;
        numeroCuentaCounter++;
        this.saldo = saldo;
        this.transacciones = transacciones;
        this.tipoDeMoneda = tipoDeMoneda;
    }

    public void extraccion(Double monto) throws SaldoInsuficienteExeption, IOException{
        if (monto > saldo) {
            throw new SaldoInsuficienteExeption("El monto pedido por la extraccion es mayor al saldo de la caja de ahorro, no se realiza ningun cambio");
        }else{
            Transaccion t = new Transaccion(false, monto);
            transacciones.add(t);
            saldo -= monto;
            try {
                FileWriter writer = new FileWriter("transacciones.txt");
                writer.append(numeroCuenta + "," + tipoDeMoneda + "," + t.toString());
                
                writer.close();
            } catch (IOException e) {
                throw new IOException();
            }
        }
    }
    public void deposito(Double monto)throws IOException{
        Transaccion t = new Transaccion(true, monto);
        transacciones.add(t);
        saldo += monto;
        try {
            FileWriter writer = new FileWriter("transacciones.txt");
            writer.append(numeroCuenta + "," + tipoDeMoneda + "," + t.toString());
            writer.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}