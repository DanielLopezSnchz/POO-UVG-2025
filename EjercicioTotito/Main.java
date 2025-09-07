public class Main {
    public static void main(String[] args) {
        Consola consola = new Consola();   // Vista
        Totito juego = new Totito(consola); // Modelo que usa la vista

        juego.jugar(); // Arranca el ciclo de juego
    }
}