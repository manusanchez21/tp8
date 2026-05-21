/**
 * Representa una tarjeta de crédito con un límite asociado.
 */
public class TarjetaDeCredito {
    private Double limite;

    /**
     * Crea la tarjeta con un límite dado.
     * 
     * @param limite límite de la tarjeta
     */
    public TarjetaDeCredito(double limite) {
        this.limite = limite;
    }

    /**
     * @return límite de la tarjeta
     */
    public Double getLimite() {
        return limite;
    }
    
    /**
     * Ajusta el límite de la tarjeta
     * 
     * @param limite nuevo límite
     */
    public void setLimite(Double limite) {
        this.limite = limite;
    }
}