import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Elección del jugador
        System.out.println("=== BIENVENIDO A LA BATALLA ===");
        System.out.println("Elige tu rol:");
        System.out.println("1. Guerrero");
        System.out.println("2. Explorador");
        int opcion = sc.nextInt();
        sc.nextLine();

        Jugador jugador;
        if (opcion == 1) {
            jugador = new Guerrero("JugadorGuerrero");
        } else {
            jugador = new Explorador("JugadorExplorador");
        }

        // Crear Sistema y enemigos
        Sistema sistema = new Sistema(jugador);
        sistema.elegirEnemigos();

        System.out.println("\n¡La batalla comienza!");
        System.out.println(sistema.mostrarEstado());

        // Bucle de batalla
        while (!sistema.batallaTerminada()) {
            System.out.println("\n=== Turno del JUGADOR ===");
            System.out.println("Acciones: 1. Atacar  2. Pasar  3. Usar ítem");
            int accionJugador = sc.nextInt();
            sc.nextLine();

            if (accionJugador == 1) {
                // Elegir enemigo objetivo
                System.out.println("Elige enemigo a atacar:");
                for (int i = 0; i < sistema.getEnemigosEnBatalla().size(); i++) {
                    System.out.println((i+1) + ". " + sistema.getEnemigosEnBatalla().get(i));
                }
                int idx = sc.nextInt() - 1;
                sc.nextLine();
                sistema.turnoJugador(sistema.getEnemigosEnBatalla().get(idx), "atacar", null);

            } else if (accionJugador == 2) {
                sistema.turnoJugador(null, "pasar", null);

            } else if (accionJugador == 3) {
                // Usar ítem
                System.out.println(jugador.mostrarItems());
                if (jugador.getItems().isEmpty()) {
                    System.out.println("No tienes ítems.");
                } else {
                    System.out.println("Elige ítem:");
                    int idxItem = sc.nextInt() - 1;
                    sc.nextLine();
                    Item elegido = jugador.getItems().get(idxItem);

                    System.out.println("Elige objetivo:");
                    System.out.println("0. A ti mismo");
                    for (int i = 0; i < sistema.getEnemigosEnBatalla().size(); i++) {
                        System.out.println((i+1) + ". " + sistema.getEnemigosEnBatalla().get(i));
                    }
                    int idxObjetivo = sc.nextInt();
                    sc.nextLine();
                    Combatiente objetivo = (idxObjetivo == 0) ? jugador : sistema.getEnemigosEnBatalla().get(idxObjetivo - 1);

                    sistema.turnoJugador(objetivo, "item", elegido);
                }
            }

            // Turno de enemigos
            for (Enemigo enemigo : sistema.getEnemigosEnBatalla()) {
                if (!enemigo.estaVivo()) continue;

                System.out.println("\n=== Turno de " + enemigo.getNombre() + " ===");
                System.out.println("Acciones: 1. Atacar  2. Pasar  3. Habilidad Especial" 
                                   + (enemigo instanceof Jefe ? "  4. Habilidad Extra" : ""));
                int accionEnemigo = sc.nextInt();
                sc.nextLine();

                Combatiente objetivo = jugador; // por simplicidad, enemigos atacan siempre al jugador

                switch (accionEnemigo) {
                    case 1:
                        sistema.turnoEnemigo(enemigo, objetivo, "atacar");
                        break;
                    case 2:
                        sistema.turnoEnemigo(enemigo, objetivo, "pasar");
                        break;
                    case 3:
                        sistema.turnoEnemigo(enemigo, objetivo, "habilidad");
                        break;
                    case 4:
                        if (enemigo instanceof Jefe) {
                            sistema.turnoEnemigo(enemigo, objetivo, "extra");
                        }
                        break;
                }
            }

            // Mostrar estado y registro
            System.out.println("\n" + sistema.mostrarEstado());
            System.out.println(sistema.mostrarRegistro());
        }

        // Fin de la batalla
        if (jugador.estaVivo()) {
            System.out.println("\n" + jugador.mensajeVictoria());
        } else {
            System.out.println("\n" + jugador.mensajeDerrota());
        }

        sc.close();
    }
}
