public class ClienteOro extends Cliente {
    private TarjetaDeCredito credix;

    public ClienteOro(int dni, String nombre, boolean estado, double saldoPesos, double saldoDolares) {
        super(dni, nombre, estado, saldoPesos, saldoDolares);
        this.credix = new TarjetaDeCredito(250000);
    }
    public double getLimiteTarjeta() {
        return credix.getLimite();
    }
}