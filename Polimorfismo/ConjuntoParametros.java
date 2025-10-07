import java.util.List;

public class ConjuntoParametros extends Parametro {
    private List<Float> valores;

    public ConjuntoParametros(String nombre, List<Float> valores) {
        super(nombre);
        this.valores = valores;
    }

    public List<Float> getValores() {
        return valores;
    }
}
