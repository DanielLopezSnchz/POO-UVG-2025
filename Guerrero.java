public class Guerrero extends Jugador {

    // Constructor
    public Guerrero(String nombre) {
        // Vida alta, ataque alto
        super(nombre, 120, 25);

        // Inventario limitado (2 ítems de inicio)
        this.items.add(new Item("Poción de Vida", 2, 30, 0));
        this.items.add(new Item("Espada Rúnica", 1, 0, 10));
    }

    // Implementación de turno
    @Override
    public void tomarTurno() {
        this.accionTomada = this.nombre + " está esperando su acción...";
        // En Sistema se decide qué acción toma el jugador.
    }
}
