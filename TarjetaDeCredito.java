public class TarjetaDeCredito {
    private Double limite;

    public TarjetaDeCredito(double limite) {
        this.limite = limite;
    }

    public Double getLimite() {
        return limite;
    }
    
    public void setLimite(Double limite) {
        this.limite = limite;
    }
}