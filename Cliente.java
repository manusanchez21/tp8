import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente {
    private Integer dni;
    private String nombre;
    private LocalDate anioIngreso;
    private Boolean estado;
    private CajaDeAhorro cajaDeAhorroPesos;
    private CajaDeAhorro cajaDeAhorroDolares;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Cliente(Integer dni, String nombre) { //constructor para un nuevo cliente
        this.dni = dni;
        this.nombre = nombre;
        this.anioIngreso = LocalDate.now();
        this.estado = true;
        this.cajaDeAhorroDolares = new CajaDeAhorro(0);
        this.cajaDeAhorroPesos = new CajaDeAhorro(1);
    }
    public Cliente(Integer dni, String nombre, Boolean estado, String anioIngreso, Double saldoPesos, Double saldoDolares, Integer numeroDeCuentaDolares, Integer numeroDeCuentaPesos, ArrayList<Transaccion> transaccionesDolares, ArrayList<Transaccion> transaccionesPesos) { // constructor para un cliente que es leido del archivo
        this.dni = dni;
        this.nombre = nombre;
        this.anioIngreso = LocalDate.parse(anioIngreso, formatter);
        this.estado = estado;
        this.cajaDeAhorroDolares = new CajaDeAhorro(numeroDeCuentaDolares, saldoDolares, 0, transaccionesDolares);
        this.cajaDeAhorroPesos = new CajaDeAhorro(numeroDeCuentaPesos, saldoPesos, 1, transaccionesPesos);
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void extraccionPesos(double monto) throws SaldoInsuficienteExeption, IOException {
        cajaDeAhorroPesos.extraccion(monto);
    }

    public void extraccionDolares(Double monto) throws SaldoInsuficienteExeption, IOException {
        cajaDeAhorroDolares.extraccion(monto);
    }

    public void depositoPesos(double monto) throws IOException {
        cajaDeAhorroPesos.deposito(monto);
    }

    public void depositoDolares(double monto) throws IOException {
        cajaDeAhorroDolares.deposito(monto);
    }

    public String toString(){
        return dni + "," + nombre + "," + anioIngreso.format(formatter) + "," + estado + "," + cajaDeAhorroDolares.getNumeroCuenta() + "," + cajaDeAhorroPesos.getNumeroCuenta();
    }

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