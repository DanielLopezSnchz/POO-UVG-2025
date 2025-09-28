public class Item {

    // Atributos
    private String nombre;
    private int cantidad;      // usos restantes
    private int efectoVida;    // positivo: cura, negativo: daña
    private int efectoAtaque;  // positivo: aumenta ataque temporal
                               
    // Constructor
    public Item(String nombre, int cantidad, int efectoVida, int efectoAtaque) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.efectoVida = efectoVida;
        this.efectoAtaque = efectoAtaque;
    }

    // Métodos principales

    // Aplica el efecto sobre un combatiente
    public boolean aplicar(Combatiente objetivo) {
        if (cantidad <= 0) {
            return false; // Ya no se puede aplicar porque se consumió
        }
        // aplicar curación o daño
        if (efectoVida != 0) {
            objetivo.puntosVida += efectoVida;
            if (objetivo.puntosVida < 0) {
                objetivo.puntosVida = 0;
            }
        }

        // aplicar aumento de ataque temporal
        if (efectoAtaque != 0) {
            objetivo.poderAtaque += efectoAtaque;
            // Este efecto solo dura 1 turno. El sistema debe restablecer su valor.
        }

        cantidad--; // se consume un uso
        return true; // se pudo usar el item.
    }

    // Saber si ya no se puede usar
    public boolean seConsumio() {
        return cantidad <= 0;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return nombre + "\nUsos: " + cantidad +
               "\nVida: " + efectoVida +
               "\nAtaque: " + efectoAtaque;
    }
}
