import java.util.List;

public class MProbabilistico extends Modelo {
    private float probabilidad;

    public MProbabilistico(String nombre, List<Parametro> parametros) {
        super(nombre, parametros);
    }

    // Calcula probabilidad: casos favorables / casos totales
    public float calcularProb(float casosFav, float casosTotales) {
        if (casosTotales <= 0) {
            probabilidad = 0; // Evita división por cero
        } else {
            probabilidad = casosFav / casosTotales;
        }
        return probabilidad;
    }

    @Override
    public void usarModelo() {
        if (parametros != null && parametros.size() >= 2 &&
            parametros.get(0) instanceof ParametroUnico &&
            parametros.get(1) instanceof ParametroUnico) {

            float casosFav = ((ParametroUnico) parametros.get(0)).getValor();
            float casosTotales = ((ParametroUnico) parametros.get(1)).getValor();
            calcularProb(casosFav, casosTotales);
        }
    }

    @Override
    public String toString() {
        return "MProbabilistico{" +
               "nombre='" + nombre + '\'' +
               ", probabilidad=" + probabilidad +
               '}';
    }
}
