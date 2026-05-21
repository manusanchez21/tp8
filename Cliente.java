import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa un cliente del banco con dos cuentas de ahorro (pesos y dólares).
 * Incluye información básica como DNI, nombre, fecha de ingreso y estado.
 */
public class Cliente {
    private Integer dni;
    private String nombre;
    private LocalDate anioIngreso;
    private Boolean estado;
    private CajaDeAhorro cajaDeAhorroPesos;
    private CajaDeAhorro cajaDeAhorroDolares;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Crea un nuevo cliente con cuentas de ahorro vacías en pesos y dólares.
     * 
     * @param dni    documento del cliente
     * @param nombre nombre del cliente
     */
    public Cliente(Integer dni, String nombre) { //constructor para un nuevo cliente
        this.dni = dni;
        this.nombre = nombre;
        this.anioIngreso = LocalDate.now();
        this.estado = true;
        this.cajaDeAhorroDolares = new CajaDeAhorro(0);
        this.cajaDeAhorroPesos = new CajaDeAhorro(1);
    }

    /**
     * Constructor usado al cargar clientes desde archivo.
     * 
     * @param dni                   documento del cliente
     * @param nombre                nombre del cliente
     * @param estado                si la cuenta está activa
     * @param anioIngreso           fecha de ingreso en formato dd-MM-yyyy
     * @param saldoPesos            saldo en pesos
     * @param saldoDolares          saldo en dólares
     * @param numeroDeCuentaDolares número de cuenta en dólares
     * @param numeroDeCuentaPesos   número de cuenta en pesos
     * @param transaccionesDolares  lista de transacciones en dólares
     * @param transaccionesPesos    lista de transacciones en pesos
     */
    public Cliente(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) { // constructor para un cliente que es leido del archivo
        this.dni = dni;
        this.nombre = nombre;
        this.anioIngreso = LocalDate.parse(anioIngreso, formatter);
        this.estado = estado;
        this.cajaDeAhorroDolares = new CajaDeAhorro(numeroDeCuentaDolares, saldoDolares, 0, transaccionesDolares);
        this.cajaDeAhorroPesos = new CajaDeAhorro(numeroDeCuentaPesos, saldoPesos, 1, transaccionesPesos);
    }

    /**
     * Cambia el estado del cliente (activo/inactivo).
     * 
     * @param estado nuevo estado
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    /**
     * Realiza una extracción en la cuenta en pesos.
     * 
     * @param monto cantidad a extraer
     * @throws SaldoInsuficienteExeption si no hay saldo suficiente
     * @throws IOException               si ocurre un error al guardar la
     *                                   transacción
     */
    public void extraccionPesos(double monto) throws SaldoInsuficienteExeption, IOException {
        cajaDeAhorroPesos.extraccion(monto);
    }

    /**
     * Realiza una extracción en la cuenta en dólares.
     * 
     * @param monto cantidad a extraer
     * @throws SaldoInsuficienteExeption si no hay saldo suficiente
     * @throws IOException               si ocurre un error al guardar la
     *                                   transacción
     */
    public void extraccionDolares(Double monto) throws SaldoInsuficienteExeption, IOException {
        cajaDeAhorroDolares.extraccion(monto);
    }

    /**
     * Realiza un depósito en la cuenta en pesos.
     * 
     * @param monto cantidad a depositar
     * @throws IOException si ocurre un error al guardar la transacción
     */
    public void depositoPesos(double monto) throws IOException {
        cajaDeAhorroPesos.deposito(monto);
    }

    /**
     * Realiza un depósito en la cuenta en dólares.
     * 
     * @param monto cantidad a depositar
     * @throws IOException si ocurre un error al guardar la transacción
     */
    public void depositoDolares(double monto) throws IOException {
        cajaDeAhorroDolares.deposito(monto);
    }

    /**
     * Obtiene el número de cuenta en dólares.
     * 
     * @return número de cuenta dólares
     */
    public int getNumeroCajaDeAhorroDolares() {
        return this.cajaDeAhorroDolares.getNumeroCuenta();
    }

    /**
     * Obtiene el número de cuenta en pesos.
     * 
     * @return número de cuenta pesos
     */
    public int getNumeroCajaDeAhorroPesos() {
        return this.cajaDeAhorroPesos.getNumeroCuenta();
    }

    /**
     * @return DNI del cliente
     */
    public Integer getDni() {
        return dni;
    }

    /**
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return true si el cliente está activo
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * @return fecha de ingreso del cliente
     */
    public LocalDate getAnioIngreso() {
        return anioIngreso;
    }

    /**
     * @return lista de transacciones en dólares
     */
    public ArrayList<Transaccion> getTransaccionesDolares() {
        return cajaDeAhorroDolares.getTransacciones();
    }

    /**
     * @return lista de transacciones en pesos
     */
    public ArrayList<Transaccion> getTransaccionesPesos() {
        return cajaDeAhorroPesos.getTransacciones();
    }

    /**
     * @return saldo en dólares
     */
    public Double getSaldoDolares() {
        return cajaDeAhorroDolares.getSaldo();
    }

    /**
     * @return saldo en pesos
     */
    public Double getSaldoPesos() {
        return cajaDeAhorroPesos.getSaldo();
    }

    /**
     * Representación en texto usada para guardar en archivo.
     */
    public String toString(){
        return dni + "," + nombre + "," + anioIngreso.format(formatter) + "," + estado + "," + cajaDeAhorroDolares.getNumeroCuenta() + "," + cajaDeAhorroPesos.getNumeroCuenta();
    }

    /**
     * Código que indica el nivel del cliente (0=estándar).
     * Las subclases sobreescriben este método.
     * 
     * @return código de nivel
     */
    public String getCodigoNivel(){
        return "0";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        
        if (o == null || getClass() != o.getClass()) return false;
        
        Cliente cliente = (Cliente) o;
        return Objects.equals(this.dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.dni);
    }
}