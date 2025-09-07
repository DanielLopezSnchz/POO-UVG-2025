import java.util.Scanner;

public class Consola {
    // Atributo
    private Scanner sc;

    // Constructor
    public Consola(){
        sc = new Scanner(System.in);

    }

    // Mostrar mensaje de bienvenida
    public void mostrarBienvenida() {
        System.out.println("------------------------------------");
        System.out.println("    Bienvenido al juego de Totito  ");
        System.out.println("------------------------------------");
    }

    // Dibujar el tablero actual
    public void dibujarTablero(Tablero t) {
        System.out.println("   1   2   3");
        for (int r = 0; r < 3; r++) {
            System.out.print((r + 1) + " ");
            for (int c = 0; c < 3; c++) {
                Marca m = t.getCelda(r, c).getMarca();
                String simbolo = " ";
                if (m == Marca.CIRCULO) simbolo = "O";
                else if (m == Marca.EQUIS) simbolo = "X";
                System.out.print(" " + simbolo + " ");
                if (c < 2) System.out.print("|");
            }
            System.out.println();
            if (r < 2) System.out.println("  ---+---+---");
        }
    }

    // Solicitar coordenadas al jugador
    public int[] solicitarCoordenadas(Marca jugador) {
        int fila = -1;
        int columna = -1;
        boolean valido = false;

        do {
            try {
                System.out.println("Turno de " + (jugador == Marca.CIRCULO ? "O" : "X"));
                System.out.print("Ingrese fila (1-3): ");
                fila = Integer.parseInt(sc.nextLine()) - 1;

                System.out.print("Ingrese columna (1-3): ");
                columna = Integer.parseInt(sc.nextLine()) - 1;

                if (fila >= 0 && fila < 3 && columna >= 0 && columna < 3) {
                    valido = true;
                } else {
                    System.out.println("Coordenadas fuera de rango. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Use números enteros.");
            }
        } while (!valido);

        return new int[]{fila, columna};
    }

    // Preguntar si desean jugar otra ronda
    public boolean preguntarContinuar() {
        String respuesta;
        boolean valido = false;
        boolean continuar = false;

        do {
            System.out.print("¿Desea jugar otra vez? (Si/No): ");
            respuesta = sc.nextLine().trim().toLowerCase();

            if (respuesta.equals("si")) {
                continuar = true;
                valido = true;
            } else if (respuesta.equals("no")) {
                continuar = false;
                valido = true;
            } else {
                System.out.println("Respuesta inválida. Escriba 'Si' o 'No'.");
            }
        } while (!valido);

        return continuar;
    }

    // Mostrar el ganador
    public void mostrarGanador(Resultado r) {
        switch (r) {
            case CIRCULO:
                System.out.println("¡Ganó el CIRCULO! (O)");
                break;
            case EQUIS:
                System.out.println("¡Ganó la EQUIS (X)!");
                break;
            case EMPATE:
                System.out.println("La partida terminó en empate.");
                break;
            default:
                System.out.println("La partida continúa.");
        }
    }

    // Método genérico para mensajesNo 
    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }
}




