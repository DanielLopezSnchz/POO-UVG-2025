// Subclase concreta EModerna
public class EModerna extends Equipo {

    // Atributos específicos
    private float energiaMax;          // en eV (energía máxima detectable o emitida)
    private float sensibilidad;        // en unidades arbitrarias (mayor -> más sensible)
    private float precisionMedicion;   // porcentaje de error (menor -> más preciso)
    private boolean requiereBlindaje;  // si necesita protección por radiación
    private String tipoDeteccion;      // ej. "fotón", "electrón", "neutrino", "rayos X"

    // Constructor
    public EModerna(String id, String nombre, String fabricante, float consumoElectrico, String caracteristicas,
                    float energiaMax, float sensibilidad, float precisionMedicion, boolean requiereBlindaje, String tipoDeteccion) {
        super(id, nombre, fabricante, consumoElectrico, caracteristicas);
        this.energiaMax = energiaMax;
        this.sensibilidad = sensibilidad;
        this.precisionMedicion = precisionMedicion;
        this.requiereBlindaje = requiereBlindaje;
        this.tipoDeteccion = tipoDeteccion;
    }

    // Getters
    public float getEnergiaMax() {
        return energiaMax;
    }

    public float getSensibilidad() {
        return sensibilidad;
    }

    public float getPrecisionMedicion() {
        return precisionMedicion;
    }

    public boolean isRequiereBlindaje() {
        return requiereBlindaje;
    }

    public String getTipoDeteccion() {
        return tipoDeteccion;
    }

    // Implementación de calcularConsumo(): Se utilizó IA como apoyo para hacer un calculo estimado del consumo eléctrico de un
    // equipo de Fisica Moderna, segun la energia maxima, sensibilidad del aparato, precision de medicion, si requiere o no blindaje por radiacion,
    // y el tipo de deteccion.
    @Override
    public float calcularConsumo() {
        // Cálculo conceptual:
        // Se parte del consumo base y luego se ajusta segun energia y precision.
        float consumo = consumoElectrico;
        // Los equipos más precisos requieren más energía de estabilización.
        consumo += (100 - precisionMedicion) * 0.05f;
        // Mayor energía máxima detectable también aumenta el gasto.
        consumo += energiaMax * 0.001f;
        // Alta sensibilidad implica necesidad de refrigeración o control térmico
        if (sensibilidad > 80) {
            consumo *= 1.15f;
        }
        // Si requiere blindaje, se considera el sistema de protección adicional.
        if (requiereBlindaje) {
            consumo += 10;
        }
        return consumo;
    }

    // compareTo (heredado de Equipo)
    @Override
    public int compareTo(Equipo otro) {
        return super.compareTo(otro);
    }

    // toString()
    @Override
    public String toString() {
        return "=== Equipo de Física Moderna ===\n" +
               super.toString() +
               "\nEnergía máxima: " + energiaMax + " eV" +
               "\nSensibilidad: " + sensibilidad +
               "\nPrecisión de medición: " + precisionMedicion + " %" +
               "\nTipo de detección: " + tipoDeteccion +
               "\nRequiere blindaje: " + (requiereBlindaje ? "Sí" : "No") +
               "\nConsumo estimado: " + calcularConsumo() + " W\n";
    }
}
