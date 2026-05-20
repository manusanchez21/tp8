public class ClientePlatino extends Cliente {
    private TarjetaDeCredito premium;

    public ClienteOro(int dni, String nombre, boolean estado) {
        super(dni, nombre, estado);
        this.premium = new TarjetaDeCredito(500000);
    }
    public double getLimiteTarjeta() {
        return premium.getLimite();
    }
}