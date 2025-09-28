public class Explorador extends Jugador {

    // Constructor
    public Explorador(String nombre) {
        // Vida y ataque normales
        super(nombre, 90, 15);

        // Inventario amplio (4 ítems de inicio)
        this.items.add(new Item("Poción de Vida", 3, 25, 0));
        this.items.add(new Item("Elixir de Fuerza", 2, 0, 15));
        this.items.add(new Item("Antídoto", 2, 20, 0));
        this.items.add(new Item("Bomba de Humo", 1, -10, 0));
    }

    // Implementación de turno
    @Override
    public void tomarTurno() {
        this.accionTomada = this.nombre + " está esperando su acción...";
        // En Sistema se decide qué acción toma el jugador.
    }
}
