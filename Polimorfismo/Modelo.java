import java.util.List;

public abstract class Modelo {
    protected String nombre;
    protected List<Parametro> parametros;

    public Modelo(String nombre, List<Parametro> parametros) {
        this.nombre = nombre;
        this.parametros = parametros;
    }

    public abstract void usarModelo();

    public String getNombre() {
        return nombre;
    }

    public List<Parametro> getParametros() {
        return parametros;
    }
}

