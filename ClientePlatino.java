public class ClientePlatino extends Cliente {
    private TarjetaDeCredito premium;

    public ClientePlatino(int dni, String nombre, boolean estado, double saldoPesos, double saldoDolares) {
        super(dni, nombre, estado, saldoPesos, saldoDolares);
        this.premium = new TarjetaDeCredito(500000);
    }
    public double getLimiteTarjeta() {
        return premium.getLimite();
    }
}