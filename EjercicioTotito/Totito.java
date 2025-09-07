public class Totito {
    // Atributos
    private Tablero tablero;
    private Consola vista;
    private int victoriasCirculo;
    private int victoriasEquis;
    private int empates;

    // Constructor
    public Totito(Consola vista) {
        this.vista = vista;
        this.tablero = new Tablero();
        this.victoriasCirculo = 0;
        this.victoriasEquis = 0;
        this.empates = 0;
    }

    // Método principal
    public void jugar() {
        vista.mostrarBienvenida();

        boolean continuar;
        do {
            this.jugarRonda();
            continuar = vista.preguntarContinuar();
            if (continuar) {
                tablero.reiniciar();
            }
        } while (continuar);

        this.vista.mostrarMensaje(this.generarInforme());
    }

    // Ejecuta una sola partida
    private void jugarRonda() {
        Marca turno = Marca.CIRCULO; // empieza el círculo
        Resultado resultado = null;

        do {
            this.vista.dibujarTablero(this.tablero);
            int[] pos = vista.solicitarCoordenadas(turno);

            boolean exito = tablero.marcar(turno, pos[0], pos[1]);
            if (!exito) {
                this.vista.mostrarMensaje("Esa casilla ya está ocupada. Intenta otra.");
                continue; // mismo jugador repite turno
            }

            resultado = tablero.comprobarGanador();

            if (resultado == Resultado.NINGUNO) {
                turno = (turno == Marca.CIRCULO) ? Marca.EQUIS : Marca.CIRCULO;
            }
        } while (resultado == Resultado.NINGUNO);

        // Mostrar tablero final y resultado
        this.vista.dibujarTablero(tablero);
        this.vista.mostrarGanador(resultado);

        // Actualizar estadísticas
        switch (resultado) {
            case CIRCULO:
                this.victoriasCirculo++;
                break;
            case EQUIS:
                this.victoriasEquis++;
                break;
            case EMPATE:
                this.empates++;
                break;
            default:
                break;
        }
    }

    // Devuelve informe como String
    private String generarInforme() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------- RESULTADOS DE LA SESIÓN ----------\n");
        sb.append("Victorias de CIRCULO (O): ").append(victoriasCirculo).append("\n");
        sb.append("Victorias de EQUIS (X): ").append(victoriasEquis).append("\n");
        sb.append("Empates: ").append(empates).append("\n");

        if (victoriasCirculo > victoriasEquis) {
            sb.append("Ganador de la sesión: CIRCULO (O)\n");
        } else if (victoriasEquis > victoriasCirculo) {
            sb.append("Ganador de la sesión: EQUIS (X)\n");
        } else {
            sb.append("La sesión terminó en empate general.\n");
        }
        return sb.toString();
    }
}