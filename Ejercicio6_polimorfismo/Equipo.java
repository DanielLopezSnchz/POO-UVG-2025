
// Clase abstracta Equipo
public abstract class Equipo implements Comparable<Equipo> {

    // Atributos protegidos
    protected String id;
    protected String nombre;
    protected String fabricante;
    protected float consumoElectrico;
    protected String caracteristicas;

    // Constructor
    public Equipo(String id, String nombre, String fabricante, float consumoElectrico, String caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.consumoElectrico = consumoElectrico;
        this.caracteristicas = caracteristicas;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFabricante() {
        return fabricante;
    }

    public float getConsumoElectrico() {
        return consumoElectrico;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    // Métodos abstractos
    public abstract float calcularConsumo();

    // Implementación Comparable
    @Override
    public int compareTo(Equipo otro) {
        // Ordenar por consumo eléctrico (de menor a mayor)
        return Float.compare(this.consumoElectrico, otro.consumoElectrico);
    }

    // toString()
    @Override
    public String toString() {
        return "ID: " + id +
               "\nNombre: " + nombre +
               "\nFabricante: " + fabricante +
               "\nConsumo eléctrico: " + consumoElectrico + " W" +
               "\nCaracterísticas: " + caracteristicas;
    }
}
