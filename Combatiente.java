public abstract class Combatiente {
    // Atributos generales de Combatiente
    protected String nombre;
    protected int puntosVida;
    protected int poderAtaque;
    protected String accionTomada;

    
    // Constructor
  
    public Combatiente(String nombre, int puntosVida, int poderAtaque) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.poderAtaque = poderAtaque;
        this.accionTomada = "Ninguna";
    }

    // Métodos generales

    // Cada combatiente debe tomar un turno, el método es requisito de cada subclase.
    public abstract void tomarTurno();

    // Atacar a un objetivo
    public void atacar(Combatiente objetivo) {
        if (objetivo.estaVivo()) {
            objetivo.puntosVida -= this.poderAtaque;
            if (objetivo.puntosVida < 0){
                objetivo.puntosVida = 0;
            }
            this.accionTomada = this.nombre + " atacó a " + objetivo.nombre + " causando " + this.poderAtaque + " de daño.";
        } else {
            this.accionTomada = this.nombre + " intentó atacar a " + objetivo.nombre + " pero ya está derrotado.";
        }
    }

    // Pasar turno
    public void pasarTurno() {
        this.accionTomada = this.nombre + " decidió pasar su turno.";
    }

    // Mensajes genéricos
    public String mensajeInicio() {
        return this.nombre + " entra en la batalla.";
    }

    public String mensajeVictoria() {
        return this.nombre + " ha ganado la batalla.";
    }

    public String mensajeDerrota() {
        return this.nombre + " ha sido derrotado.";
    }

    // Metodos adicionales, getters y setters.

    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public String getAccionTomada() {
        return accionTomada;
    }

    public void setAccionTomada(String accionTomada) {
        this.accionTomada = accionTomada;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntosVida() {
        return puntosVida;
    }

    public int getPoderAtaque() {
        return poderAtaque;
    }

    @Override
    public String toString() {
        return this.nombre + "\nNivel de vida: " + this.puntosVida + "\nNivel de ataque: " + this.poderAtaque;
    }
}
