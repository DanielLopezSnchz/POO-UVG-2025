import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sistema {

    // Atributos
    private Jugador jugador;
    private List<Enemigo> enemigosEnBatalla;
    private List<Enemigo> bancoEnemigos;
    private List<String> registroAcciones;
    private Random random;

    // Constructor
    public Sistema(Jugador jugador) {
        this.jugador = jugador;
        this.enemigosEnBatalla = new ArrayList<>();
        this.bancoEnemigos = new ArrayList<>();
        this.registroAcciones = new ArrayList<>();
        this.random = new Random();
        inicializarBancoEnemigos();
    }

    // Banco de enemigos
    private void inicializarBancoEnemigos() {
        // 15 enemigos normales
        for (int i = 1; i <= 15; i++) {
            HabilidadEspecial habilidad = (i % 2 == 0) ? HabilidadEspecial.ENVENENAR : HabilidadEspecial.CURAR;
            bancoEnemigos.add(new Enemigo("Enemigo" + i, 60 + i*2, 10 + i, habilidad));
        }

        // 5 jefes
        bancoEnemigos.add(new Jefe("JefeOrco", 150, 30, HabilidadEspecial.CURAR, HabilidadEspecial.DOBLE_ATAQUE));
        bancoEnemigos.add(new Jefe("JefeTroll", 160, 28, HabilidadEspecial.ENVENENAR, HabilidadEspecial.ESQUIVAR));
        bancoEnemigos.add(new Jefe("JefeDragón", 200, 35, HabilidadEspecial.DOBLE_ATAQUE, HabilidadEspecial.CURAR));
        bancoEnemigos.add(new Jefe("JefeLiche", 140, 25, HabilidadEspecial.ENVENENAR, HabilidadEspecial.DOBLE_ATAQUE));
        bancoEnemigos.add(new Jefe("JefeTitán", 220, 40, HabilidadEspecial.ESQUIVAR, HabilidadEspecial.CURAR));
    }

    // Inicializar batalla
    public void elegirEnemigos() {
        enemigosEnBatalla.clear();
        int cantidad = random.nextInt(3) + 1; // 1 a 3 enemigos
        for (int i = 0; i < cantidad; i++) {
            int index = random.nextInt(bancoEnemigos.size());
            enemigosEnBatalla.add(bancoEnemigos.get(index));
        }
    }

    // Turno del jugador
    public void turnoJugador(Combatiente objetivo, String accion, Item item) {
        if (!jugador.estaVivo()) return;

        switch (accion) {
            case "atacar":
                jugador.atacar(objetivo);
                registrarAccion(jugador.getAccionTomada());
                break;

            case "pasar":
                jugador.pasarTurno();
                registrarAccion(jugador.getAccionTomada());
                break;

            case "item":
                jugador.usarItem(item, objetivo);
                registrarAccion(jugador.getAccionTomada());
                break;
        }
    }

    // Turno de enemigos
    public void turnoEnemigo(Enemigo enemigo, Combatiente objetivo, String accion) {
        if (!enemigo.estaVivo()) return;

        switch (accion) {
            case "atacar":
                enemigo.atacar(objetivo);
                registrarAccion(enemigo.getAccionTomada());
                break;

            case "pasar":
                enemigo.pasarTurno();
                registrarAccion(enemigo.getAccionTomada());
                break;

            case "habilidad":
                enemigo.usarHabilidadEspecial(objetivo);
                registrarAccion(enemigo.getAccionTomada());
                break;

            case "extra":
                if (enemigo instanceof Jefe) {
                    ((Jefe) enemigo).usarHabilidadExtra(objetivo);
                    registrarAccion(enemigo.getAccionTomada());
                } else {
                    registrarAccion(enemigo.getNombre() + " no tiene habilidad extra.");
                }
                break;
        }
    }

    // Registro de acciones
    private void registrarAccion(String accion) {
        if (registroAcciones.size() == 3) {
            registroAcciones.remove(0);
        }
        registroAcciones.add(accion);
    }

    public String mostrarRegistro() {
        StringBuilder sb = new StringBuilder("Ultimas acciones:\n");
        for (String accion : registroAcciones) {
            sb.append("- ").append(accion).append("\n");
        }
        return sb.toString();
    }

    // Estado de batalla
    public String mostrarEstado() {
        StringBuilder sb = new StringBuilder("=== ESTADO DE LA BATALLA ===\n");
        sb.append("Jugador: ").append(jugador.toString()).append("\n");
        for (Enemigo e : enemigosEnBatalla) {
            sb.append("Enemigo: ").append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    public boolean batallaTerminada() {
        // Si el jugador está muerto, la batalla termina
        if (!jugador.estaVivo()) {
            return true;

        }
        // Revisar si todos los enemigos están muertos
        boolean todosMuertos = true;
        for (Enemigo e : enemigosEnBatalla) {
            if (e.estaVivo()) {
                todosMuertos = false;
                break;
            }
        }
        return todosMuertos;
    }

    // Getters
    public Jugador getJugador() {
        return jugador;
    }

    public List<Enemigo> getEnemigosEnBatalla() {
        return enemigosEnBatalla;
    }
}

