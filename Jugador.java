import java.util.ArrayList;
import java.util.List;

public abstract class Jugador extends Combatiente {

    // Atributos
    protected List<Item> items;   // Inventario de ítems

    // Constructor
    public Jugador(String nombre, int puntosVida, int poderAtaque) {
        super(nombre, puntosVida, poderAtaque);
        this.items = new ArrayList<>();
    }

    // Métodos principales

    // Cada subclase (Guerrero, Explorador) define como se comporta en su turno.
    @Override
    public abstract void tomarTurno();

    // Usar ítem sobre un objetivo
    public void usarItem(Item item, Combatiente objetivo) {
        if (items.contains(item) && !item.seConsumio()) {
            item.aplicar(objetivo);
            this.accionTomada = this.nombre + " usó " + item.getNombre() + " en " + objetivo.getNombre();

            if (item.seConsumio()) {
                items.remove(item); // Si ya no quedan usos, se elimina del inventario
            }
        } else {
            this.accionTomada = this.nombre + " intentó usar un ítem inválido.";
        }
    }

    // --- Metodos nuevos, no considerados en el diagrama UML ---
    // Agregar ítem al inventario
    public void agregarItem(Item item) {
        this.items.add(item);
    }

    // Mostrar inventario
    public String mostrarItems() {
        if (items.isEmpty()) {
            return nombre + " no tiene ítems.";
        }

        StringBuilder sb = new StringBuilder("Inventario de " + nombre + ":\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append((i + 1)).append(". ").append(items.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
    // --------------------------------------------------------------

    // Getters
    public List<Item> getItems() {
        return items;
    }
}
