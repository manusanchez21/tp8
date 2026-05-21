import java.io.IOException;
import java.time.LocalDate;

public class Cliente {
    private Integer dni;
    private String nombre;
    private LocalDate anioIngreso;
    private Boolean estado;
    private CajaDeAhorro cajaDeAhorroPesos;
    private CajaDeAhorro cajaDeAhorroDolares;

    public Cliente(int dni, String nombre, boolean estado, double saldoPesos, double saldoDolares) {
        this.dni = dni;
        this.nombre = nombre;
        this.anioIngreso = LocalDate.now();
        this.estado = estado;
        this.cajaDeAhorroDolares = new CajaDeAhorro(0);
        this.cajaDeAhorroPesos = new CajaDeAhorro(1);
    }

    public LocalDate getAnioIngreso() {
        return anioIngreso;
    }

    public Integer getDni() {
        return dni;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getNombre() {
        return nombre;
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
}