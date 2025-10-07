public class ParametroUnico extends Parametro {
    private float valor;

    public ParametroUnico(String nombre, float valor) {
        super(nombre);
        this.valor = valor;
    }

    public float getValor() {
        return valor;
    }
}
