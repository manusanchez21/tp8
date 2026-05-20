import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Cliente {
    private Integer dni;
    private String nombre;
    private LocalDate anioIngreso;
    private Boolean estado;
    private CajaDeAhorro cajaDeAhorroPesos;
    private CajaDeAhorro cajaDeAhorroDolares;

    public Cliente(int dni, String nombre, boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.anioIngreso = LocalDate.now();
        this.estado = true;
        this.cajaDeAhorroDolares = new CajaDeAhorro();
        this.cajaDeAhorroPesos = new CajaDeAhorro();
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

    public double getSaldoPesos() {
        return this.cajaDeAhorroPesos.getSaldo();
    }

    public double getSaldoDolares() {
        return this.cajaDeAhorroDolares.getSaldo();
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void extraccionPesos(double cantidad) {
        cajaDeAhorroPesos.extraccion(cantidad);
        registrarTransaccion(cantidad, false, this.cajaDeAhorroPesos.getNumeroCuenta());
    }

    public void extraccionDolares(double cantidad) {
        cajaDeAhorroDolares.extraccion(cantidad);
        registrarTransaccion(cantidad, false, this.cajaDeAhorroDolares.getNumeroCuenta());
    }

    public void depositoPesos(double cantidad) {
        cajaDeAhorroPesos.deposito(cantidad);
        registrarTransaccion(cantidad, true, this.cajaDeAhorroPesos.getNumeroCuenta());
    }

    public void depositoDolares(double cantidad) {
        cajaDeAhorroDolares.deposito(cantidad);
        registrarTransaccion(cantidad, true, this.cajaDeAhorroDolares.getNumeroCuenta());
    }

    private void registrarTransaccion(double cantidad, boolean tipo, int numeroCuenta) {
        try {
            FileWriter writer = new FileWriter("transacciones.txt");
            String tipoString = "";
            if (tipo) {
                tipoString = "extraccion";
            } else {
                tipoString = "deposito";
            }

            writer.append("Numero cuenta: " + numeroCuenta + ", tipo: " + tipoString + ", monto: " + cantidad);

            writer.close();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}