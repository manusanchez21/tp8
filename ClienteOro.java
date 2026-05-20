public class ClienteOro extends Cliente {
    private TarjetaDeCredito credix;

    public ClienteOro(int dni, String nombre, boolean estado) {
        super(dni, nombre, estado);
        this.credix = new TarjetaDeCredito(250000);
    }
    public double getLimiteTarjeta() {
        return credix.getLimite();
    }
}