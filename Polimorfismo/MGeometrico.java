import java.util.List;

public class MGeometrico extends Modelo {
    public enum Figura { CUADRILATERO, TRIANGULO, CIRCULO, TRAPECIO }
    private float area;
    private Figura figura;

    public MGeometrico(String nombre, List<Parametro> parametros, Figura figura) {
        super(nombre, parametros);
        this.figura = figura;
    }

    public float calcularAreaCuadrado(float base, float altura) {
        area = base * altura;
        return area;
    }

    public float calcularAreaTriangulo(float base, float altura) {
        area = (base * altura) / 2.0f;
        return area;
    }

    public float calcularAreaCirculo(float radio) {
        area = (float) (Math.PI * radio * radio);
        return area;
    }

    public float calcularAreaTrapecio(float base1, float base2, float altura) {
        area = ((base1 + base2) * altura) / 2.0f;
        return area;
    }

    @Override
    public void usarModelo() {
        // Utiliza los parámetros según la figura, se usa switch case.
        if (parametros != null) {
            switch (figura) {
                case CUADRILATERO:
                    // Se supone aqui que base y altura son los primeros 2 parametros de tipo ParametroUnico
                    if (parametros.size() >= 2 &&
                        parametros.get(0) instanceof ParametroUnico &&
                        parametros.get(1) instanceof ParametroUnico) {
                            float base = ((ParametroUnico) parametros.get(0)).getValor();
                            float altura = ((ParametroUnico) parametros.get(1)).getValor();
                            calcularAreaCuadrado(base, altura);
                        }
                        break;
                case TRIANGULO:
                    if (parametros.size() >= 2 &&
                        parametros.get(0) instanceof ParametroUnico &&
                        parametros.get(1) instanceof ParametroUnico) {
                            float base = ((ParametroUnico) parametros.get(0)).getValor();
                            float altura = ((ParametroUnico) parametros.get(1)).getValor();
                            calcularAreaTriangulo(base, altura);
                        }
                        break;
                case CIRCULO:
                    if (parametros.size() >= 1 &&
                        parametros.get(0) instanceof ParametroUnico) {
                            float radio = ((ParametroUnico) parametros.get(0)).getValor();
                            calcularAreaCirculo(radio);
                        }
                        break;
                case TRAPECIO:
                    if (parametros.size() >= 3 &&
                        parametros.get(0) instanceof ParametroUnico &&
                        parametros.get(1) instanceof ParametroUnico &&
                        parametros.get(2) instanceof ParametroUnico) {
                            float base1 = ((ParametroUnico) parametros.get(0)).getValor();
                            float base2 = ((ParametroUnico) parametros.get(1)).getValor();
                            float altura = ((ParametroUnico) parametros.get(2)).getValor();
                            calcularAreaTrapecio(base1, base2, altura);
                    }
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "MGeometrico{" +
               "nombre='" + nombre + '\'' +
               ", figura=" + figura +
               ", area=" + area +
               '}';
    }
}
