// Subclase concreta EElectromagnetismo
public class EElectromagnetismo extends Equipo {

    // Atributos específicos
    private float voltajeMax;         // en Voltios (V)
    private float corrienteMax;       // en Amperios (A)
    private String tipoConexion;      // ej. "monofásico", "trifásico", "DC"
    private float freqOperacion;      // en Hertz (Hz)
    private boolean tieneProteccion;  // indica si tiene fusibles o cortacircuitos

    // Constructor
    public EElectromagnetismo(String id, String nombre, String fabricante, float consumoElectrico, String caracteristicas,
                              float voltajeMax, float corrienteMax, String tipoConexion, float freqOperacion, boolean tieneProteccion) {
        super(id, nombre, fabricante, consumoElectrico, caracteristicas);
        this.voltajeMax = voltajeMax;
        this.corrienteMax = corrienteMax;
        this.tipoConexion = tipoConexion;
        this.freqOperacion = freqOperacion;
        this.tieneProteccion = tieneProteccion;
    }

    // Getters
    public float getVoltajeMax() {
        return voltajeMax;
    }

    public float getCorrienteMax() {
        return corrienteMax;
    }

    public String getTipoConexion() {
        return tipoConexion;
    }

    public float getFreqOperacion() {
        return freqOperacion;
    }

    public boolean isTieneProteccion() {
        return tieneProteccion;
    }

    // Implementación de calcularConsumo(): Se utilizó IA como apoyo para hacer un calculo estimado del consumo eléctrico de un
    // equipo de electromagnetismo, segun el voltaje maximo, corriente maxima, tipo de conexion, frecuencia de operacion, y si tiene o no
    // proteccion.
    @Override
    public float calcularConsumo() {
        // Cálculo estimado:
        // Potencia teórica = V * I
        float potencia = voltajeMax * corrienteMax;

        // Ajuste según tipo de conexión
        if (tipoConexion.equalsIgnoreCase("trifásico")) {
            potencia *= 1.2f; // consumo mayor por fases múltiples
        } else if (tipoConexion.equalsIgnoreCase("DC")) {
            potencia *= 0.9f; // menor pérdida en corriente continua
        }

        // Ajuste por frecuencia (simula pérdidas por histéresis o calor)
        if (freqOperacion > 60) {
            potencia *= 1.1f;
        }

        // Si tiene protección eléctrica, se considera un ligero aumento (fusibles, relés)
        if (tieneProteccion) {
            potencia += 5; 
        }

        // Promedio ponderado con consumo nominal
        float consumoFinal = (consumoElectrico + potencia * 0.001f);

        return consumoFinal;
    }

    // compareTo (usa el de la superclase)
    @Override
    public int compareTo(Equipo otro) {
        return super.compareTo(otro);
    }

    // toString()
    @Override
    public String toString() {
        return "=== Equipo de Electromagnetismo ===\n" +
               super.toString() +
               "\nVoltaje máximo: " + voltajeMax + " V" +
               "\nCorriente máxima: " + corrienteMax + " A" +
               "\nTipo de conexión: " + tipoConexion +
               "\nFrecuencia de operación: " + freqOperacion + " Hz" +
               "\nProtección eléctrica: " + (tieneProteccion ? "Sí" : "No") +
               "\nConsumo estimado: " + calcularConsumo() + " W\n";
    }
}
