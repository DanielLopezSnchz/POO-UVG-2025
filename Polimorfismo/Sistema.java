import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Modelo> modelos;
    private List<String> historial;

    public Sistema() {
        this.modelos = new ArrayList<>();
        this.historial = new ArrayList<>();
    }

    // Agrega un modelo al sistema
    public void agregarModelo(Modelo modelo) {
        modelos.add(modelo);
        historial.add("Se agregó modelo: " + modelo.getNombre());
    }

    // Usa un modelo por índice y almacena el resultado en el historial
    public void usarModelo(int indice) {
        if (indice >= 0 && indice < modelos.size()) {
            Modelo modelo = modelos.get(indice);
            modelo.usarModelo();
            historial.add("Se utilizó modelo: " + modelo.getNombre() + " | Resultado: " + modelo.toString());
        } else {
            historial.add("Intento inválido de uso de modelo en índice: " + indice);
        }
    }

    // Devuelve el historial completo de acciones
    public List<String> getHistorial() {
        return historial;
    }

    // Lista los nombres de todos los modelos registrados
    public List<String> listarModelos() {
        List<String> nombres = new ArrayList<>();
        for (Modelo m : modelos) {
            nombres.add(m.getNombre());
        }
        return nombres;
    }
}
