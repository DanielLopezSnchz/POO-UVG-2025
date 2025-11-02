import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sistema {

    // Atributos
    private List<Equipo> catalogo;

    // Constructor
    public Sistema() {
        catalogo = new ArrayList<>();
        cargarDatosIniciales();
    }

    // Carga inicial simulada
    private void cargarDatosIniciales() {
        catalogo.add(new EMecanica(
            "M001",
            "Péndulo con encoder",
            "Pasco",
            25.5f,
            "Longitud ajustable, base antivibración",
            "Encoder",   // tipoSensor (String)
            1.2f,        // rangoMedicion (float) en metros
            0.001f,      // precision (float)
            "m",         // unidadMedida (String)
            true         // requiereCalibracion (boolean)
        ));

        catalogo.add(new EOptica(
            "O001", "Láser Helio-Neón", "Thorlabs", 45.0f,
            "Haz estable para interferometría",
            632.8f, 5.0f, "Láser", "Visible", "Vidrio óptico"
        ));

        catalogo.add(new EElectromagnetismo(
            "E001", "Fuente de alimentación DC", "Tektronix", 60.0f,
            "Protección térmica y voltaje regulable",
            30.0f, 2.0f, "DC", 0.0f, true
        ));

        catalogo.add(new EModerna(
            "MD001", "Detector Geiger-Müller", "Ludlum", 40.0f,
            "Cuenta radiación beta y gamma",
            1500.0f, 85.0f, 2.0f, true, "rayos gamma"
        ));
    }

    // Operaciones principales

    //Devuelve una copia inmutable del catálogo
    public List<Equipo> getCatalogo() {
        return Collections.unmodifiableList(catalogo);
    }

    // Agrega un nuevo equipo al catálogo
    public void agregarEquipo(Equipo equipo) {
        if (equipo != null) {
            catalogo.add(equipo);
        }
    }

    // Busca un equipo por ID o nombre (ignora mayúsculas/minúsculas)
    public Equipo buscarEquipo(String idONombre) {
        for (Equipo e : catalogo) {
            if (e.getId().equalsIgnoreCase(idONombre) ||
                e.getNombre().equalsIgnoreCase(idONombre)) {
                return e;
            }
        }
        return null;
    }

    // Ordena el catálogo según el consumo eléctrico (ascendente)
    public void ordenarPorConsumo() {
        Collections.sort(catalogo); // usa compareTo() de Equipo
    }

    // Elimina un equipo por su identificador
    public boolean eliminarEquipo(String id) {
        return catalogo.removeIf(e -> e.getId().equalsIgnoreCase(id));
    }

    // Devuelve una lista de todos los nombres de equipos (para interfaz)
    public List<String> listarNombresEquipos() {
        List<String> nombres = new ArrayList<>();
        for (Equipo e : catalogo) {
            nombres.add(e.getNombre());
        }
        return nombres;
    }
}
