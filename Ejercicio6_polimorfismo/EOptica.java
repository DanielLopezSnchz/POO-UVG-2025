// Subclase concreta EOptica
public class EOptica extends Equipo {

    // Atributos específicos
    private float longitudOnda;      // en nanómetros (nm)
    private float potenciaOptica;    // en miliwatts (mW)
    private String tipoFuente;       // ej. láser, LED, lámpara halógena
    private String rangoEspectral;   // ej. visible, infrarrojo, ultravioleta
    private String materialOptico;   // ej. vidrio, cuarzo, plástico

    // Constructor
    public EOptica(String id, String nombre, String fabricante, float consumoElectrico, String caracteristicas,
                   float longitudOnda, float potenciaOptica, String tipoFuente, String rangoEspectral, String materialOptico) {
        super(id, nombre, fabricante, consumoElectrico, caracteristicas);
        this.longitudOnda = longitudOnda;
        this.potenciaOptica = potenciaOptica;
        this.tipoFuente = tipoFuente;
        this.rangoEspectral = rangoEspectral;
        this.materialOptico = materialOptico;
    }

    // Getters
    public float getLongitudOnda() {
        return longitudOnda;
    }

    public float getPotenciaOptica() {
        return potenciaOptica;
    }

    public String getTipoFuente() {
        return tipoFuente;
    }

    public String getRangoEspectral() {
        return rangoEspectral;
    }

    public String getMaterialOptico() {
        return materialOptico;
    }

    // Implementación del método abstracto
    @Override
    public float calcularConsumo() {
        // Cálculo hipotético: consumo base + factor por potencia óptica
        // Si es láser, aumentamos el consumo, si es LED, lo reduce
        float consumo = consumoElectrico;
        
        if (tipoFuente.equalsIgnoreCase("láser")) {
            consumo *= 1.2f;
        } else if (tipoFuente.equalsIgnoreCase("LED")) {
            consumo *= 0.9f;
        }

        // Aumento proporcional a la potencia óptica
        consumo += potenciaOptica * 0.02f;

        return consumo;
    }

    // compareTo (usa el de la superclase)
    @Override
    public int compareTo(Equipo otro) {
        return super.compareTo(otro);
    }

    // toString()
    @Override
    public String toString() {
        return "=== Equipo Óptico ===\n" +
               super.toString() +
               "\nLongitud de onda: " + longitudOnda + " nm" +
               "\nPotencia óptica: " + potenciaOptica + " mW" +
               "\nTipo de fuente: " + tipoFuente +
               "\nRango espectral: " + rangoEspectral +
               "\nMaterial óptico: " + materialOptico +
               "\nConsumo estimado: " + calcularConsumo() + " W\n";
    }
}
