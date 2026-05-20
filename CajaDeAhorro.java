import java.util.ArrayList;

public class CajaDeAhorro {
    private Double saldo;
    private ArrayList<Transaccion> transacciones;
    private Boolean tipoDeMoneda;

    CajaDeAhorro(Boolean tipoDeMoneda){
        this.saldo = 0.0;
        this.tipoDeMoneda = tipoDeMoneda;
        this.transacciones = new ArrayList<Transaccion>();
    }
    CajaDeAhorro(Boolean tipoDeMoneda, Double saldo){
        this.saldo = saldo;
        this.tipoDeMoneda = tipoDeMoneda;
        this.transacciones = new ArrayList<Transaccion>();
    }

    public void extraccion(Double monto) throws saldoInsuficienteExeption{
        if (monto > saldo) {
            throw new saldoInsuficienteExeption("El monto pedido por la extraccion es mayor al saldo de la caja de ahorro, no se realiza ningun cambio");
        }else{
            transacciones.add(new Transaccion(0, monto));
            saldo -= monto;
        }
    }
    public void deposito(Double monto){
        transacciones.add(new Transaccion(1, monto));
        saldo += monto;
    }

}