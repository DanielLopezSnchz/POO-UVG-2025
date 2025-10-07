import java.util.List;

public class MEstadistico extends Modelo {
    private float media;
    private float desvEst;
    private float varianza;

    public MEstadistico(String nombre, List<Parametro> parametros) {
        super(nombre, parametros);
    }

    // Calcula la media aritmética de los valores
    public float sacarMedia(List<Float> conjuntoDatos) {
        if (conjuntoDatos == null || conjuntoDatos.isEmpty()) return 0;
        float suma = 0;
        for (float valor : conjuntoDatos) {
            suma += valor;
        }
        media = suma / conjuntoDatos.size();
        return media;
    }

    // Calcula la desviación estándar
    public float sacarDesvEst(List<Float> conjuntoDatos) {
        if (conjuntoDatos == null || conjuntoDatos.isEmpty()) return 0;
        float promedio = sacarMedia(conjuntoDatos);
        float sumaCuadrados = 0;
        for (float valor : conjuntoDatos) {
            sumaCuadrados += Math.pow(valor - promedio, 2);
        }
        desvEst = (float) Math.sqrt(sumaCuadrados / conjuntoDatos.size());
        return desvEst;
    }

    // Calcula la varianza (cuadrado de la desviación estándar)
    public float sacarVarianza(List<Float> conjuntoDatos) {
        if (conjuntoDatos == null || conjuntoDatos.isEmpty()) return 0;
        float promedio = sacarMedia(conjuntoDatos);
        float sumaCuadrados = 0;
        for (float valor : conjuntoDatos) {
            sumaCuadrados += Math.pow(valor - promedio, 2);
        }
        varianza = sumaCuadrados / conjuntoDatos.size();
        return varianza;
    }

    // Uso de polimorfismo
    @Override
    public void usarModelo() {
        if (parametros != null && !parametros.isEmpty() && parametros.get(0) instanceof ConjuntoParametros) {
            List<Float> datos = ((ConjuntoParametros) parametros.get(0)).getValores();
            sacarMedia(datos);
            sacarDesvEst(datos);
            sacarVarianza(datos);
        }
    }

    @Override
    public String toString() {
        return "MEstadistico{" +
                "nombre='" + nombre + '\'' +
                ", media=" + media +
                ", desvEst=" + desvEst +
                ", varianza=" + varianza +
                '}';
    }
}

