public class Enemigo extends Combatiente {

    // Atributos
    protected HabilidadEspecial habilidadEspecial;

    // Constructor
    public Enemigo(String nombre, int puntosVida, int poderAtaque, HabilidadEspecial habilidad) {
        super(nombre, puntosVida, poderAtaque);
        this.habilidadEspecial = habilidad;
    }

    // Métodos
    @Override
    public void tomarTurno() {
        this.accionTomada = this.nombre + " está esperando su acción...";
    }

    //Aplica la habilidad especial sobre un objetivo.
    public void usarHabilidadEspecial(Combatiente objetivo) {
        switch (habilidadEspecial) {
            case CURAR:
                objetivo.puntosVida += 20;
                this.accionTomada = this.nombre + " usó CURAR sobre " + objetivo.getNombre();
                break;

            case ENVENENAR:
                objetivo.puntosVida -= 10;
                if (objetivo.puntosVida < 0) objetivo.puntosVida = 0;
                this.accionTomada = this.nombre + " envenenó a " + objetivo.getNombre();
                break;

            case ESQUIVAR:
                this.accionTomada = nombre + " se preparó para esquivar el próximo ataque.";
                // Esto requiere que el Sistema respete el estado de "esquivar".
                break;

            case DOBLE_ATAQUE:
                objetivo.puntosVida -= (2 * this.poderAtaque);
                if (objetivo.puntosVida < 0) objetivo.puntosVida = 0;
                this.accionTomada = nombre + " usó doble ataque contra " + objetivo.getNombre();
                break;
        }
    }

    // Getter
    public HabilidadEspecial getHabilidadEspecial() {
        return this.habilidadEspecial;
    }
}
