public class Jefe extends Enemigo {

    // Atributos adicionales
    private HabilidadEspecial habilidadExtra;

    // Constructor
    public Jefe(String nombre, int puntosVida, int poderAtaque, 
                HabilidadEspecial habilidad, HabilidadEspecial habilidadExtra) {
        super(nombre, puntosVida, poderAtaque, habilidad);
        this.habilidadExtra = habilidadExtra;
    }

    // Métodos

    public void usarHabilidadExtra(Combatiente objetivo) {
        switch (habilidadExtra) {
            case CURAR:
                objetivo.puntosVida += 40; // más fuerte que el normal
                this.accionTomada = nombre + " usó su habilidad extra: Curar. Sobre " + objetivo.getNombre();
                break;

            case ENVENENAR:
                objetivo.puntosVida -= 20;
                if (objetivo.puntosVida < 0) objetivo.puntosVida = 0;
                this.accionTomada = nombre + " usó su habilidad extra: Envenenar. Sobre " + objetivo.getNombre();
                break;

            case ESQUIVAR:
                this.accionTomada = nombre + " activó su habilidad extra: Esquivar";
                break;

            case DOBLE_ATAQUE:
                objetivo.puntosVida -= (3 * this.poderAtaque); // más daño
                if (objetivo.puntosVida < 0) objetivo.puntosVida = 0;
                this.accionTomada = this.nombre + " lanzó su habilidad extra: doble ataque. Contra " + objetivo.getNombre();
                break;
        }
    }

    // Getter
    public HabilidadEspecial getHabilidadExtra() {
        return habilidadExtra;
    }
}

