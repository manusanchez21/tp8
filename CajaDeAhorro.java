import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Representa una cuenta de ahorro simple que registra transacciones y saldo.
 * Puede ser de tipo dólares (0) o pesos (1) y persiste transacciones en
 * el archivo `transacciones.txt`.
 *
 * @author Jano Chiambretto
 * @author Manuel Sanchez Fossa
 * @version 1.0.0
 */
public class CajaDeAhorro {
    private static int numeroCuentaCounter = 0; // aca hay un error de la primera vexz q leemos el archivo
    private Integer numeroCuenta;
    private Double saldo;
    private ArrayList<Transaccion> transacciones;
    private Integer tipoDeMoneda; // 0 dolares 1 pesos argentinos

    /**
     * Crea una nueva caja de ahorro vacía.
     * 
     * @param tipoDeMoneda 0=dólares, 1=pesos
     */
    CajaDeAhorro(Integer tipoDeMoneda){
        this.saldo = 0.0;
        this.numeroCuenta = numeroCuentaCounter;
        numeroCuentaCounter++;
        this.transacciones = new ArrayList<Transaccion>();
        this.tipoDeMoneda = tipoDeMoneda;
    }

    /**
     * Crea una caja de ahorro usando los datos leídos (número de cuenta, saldo y
     * transacciones).
     * Actualiza el contador de números de cuenta si es necesario.
     */
    CajaDeAhorro(Integer numeroCuenta, Double saldo, Integer tipoDeMoneda, ArrayList<Transaccion> transacciones){
        this.numeroCuenta = numeroCuenta;
        if (numeroCuenta >= numeroCuentaCounter) {
            numeroCuentaCounter = numeroCuenta + 1;
        }
        this.saldo = saldo;
        this.transacciones = transacciones;
        this.tipoDeMoneda = tipoDeMoneda;
    }

    /**
     * Realiza una extracción si hay saldo suficiente y guarda la transacción.
     * 
     * @param monto monto a extraer
     * @throws SaldoInsuficienteExeption si no hay saldo
     * @throws IOException               si hay error al persistir la transacción
     */
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
    
    /**
     * Realiza un depósito y guarda la transacción.
     * 
     * @param monto monto a depositar
     * @throws IOException si hay error al persistir la transacción
     */
    public void deposito(Double monto) throws IOException{
        Transaccion t = new Transaccion(tipoDeMoneda, 1, monto);
        transacciones.add(t);
        saldo += monto;
        guardarTransaccion(t);
    }

    /**
     * Guarda en `transacciones.txt` la transacción asociada a esta cuenta.
     */
    private void guardarTransaccion(Transaccion t) throws IOException{
        try (FileWriter writer = new FileWriter("transacciones.txt", true);){ // esto es try-with-resources, hace que writer.close se ejecute siempre
            writer.append(numeroCuenta + "," + tipoDeMoneda + "," + t.toString() + "\n");
        } catch (IOException e) {
            throw new IOException("Error al guardar el deposito en el archivo", e);
        }
    }

    /**
     * @return número de cuenta
     */
    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @return saldo disponible
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * @return lista de transacciones asociadas a esta cuenta
     */
    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    /**
     * @return tipo de moneda (0=dólares, 1=pesos)
     */
    public Integer getTipoDeMoneda() {
        return tipoDeMoneda;
    }

    /**
     * Ajusta el contador estático de números de cuenta (útil al leer datos).
     */
    public static void setNumeroCuentaCounter(int numeroCuentaCounter) {
        CajaDeAhorro.numeroCuentaCounter = numeroCuentaCounter;
    }
}