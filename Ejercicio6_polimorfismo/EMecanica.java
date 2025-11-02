// Subclase concreta EMecanica
public class EMecanica extends Equipo {

    // Atributos específicos
    private String tipoSensor;
    private float rangoMedicion;
    private float precision;
    private String unidadMedida;
    private boolean requiereCalibracion;

    // Constructor
    public EMecanica(String id, String nombre, String fabricante, float consumoElectrico, String caracteristicas,
                     String tipoSensor, float rangoMedicion, float precision, String unidadMedida, boolean requiereCalibracion) {
        super(id, nombre, fabricante, consumoElectrico, caracteristicas);
        this.tipoSensor = tipoSensor;
        this.rangoMedicion = rangoMedicion;
        this.precision = precision;
        this.unidadMedida = unidadMedida;
        this.requiereCalibracion = requiereCalibracion;
    }

    // Getters
    public String getTipoSensor() {
        return tipoSensor;
    }

    public float getRangoMedicion() {
        return rangoMedicion;
    }

    public float getPrecision() {
        return precision;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public boolean isRequiereCalibracion() {
        return requiereCalibracion;
    }

    // Implementación del método abstracto
    @Override
    public float calcularConsumo() { // 
        // Cálculo hipotético: consumo base ajustado por calibración y rango
        float consumo = consumoElectrico;
        if (requiereCalibracion) {
            consumo *= 1.1f; // 10% más si requiere calibración
        }
        consumo += rangoMedicion * 0.05f; // pequeño incremento por el rango de medición
        return consumo;
    }

    @Override
    public int compareTo(Equipo otro) {
        return super.compareTo(otro);
    }

    // toString()
    @Override
    public String toString() {
        return "=== Equipo Mecánico ===\n" +
               super.toString() +
               "\nTipo de sensor: " + tipoSensor +
               "\nRango de medición: " + rangoMedicion + " " + unidadMedida +
               "\nPrecisión: ±" + precision +
               "\nRequiere calibración: " + (requiereCalibracion ? "Sí" : "No") +
               "\nConsumo estimado: " + calcularConsumo() + " W\n";
    }
}
