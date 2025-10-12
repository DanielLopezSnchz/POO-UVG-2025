public class GestorSiniestros extends Empleado {
    private int reclamosProcesados;
    private double incentivoPorReclamo;

    public GestorSiniestros(String nombre, int experiencia, double salarioBase,
                            int reclamosProcesados, double incentivoPorReclamo) {
        super(nombre, "Siniestros", experiencia, salarioBase);
        this.reclamosProcesados = reclamosProcesados;
        this.incentivoPorReclamo = incentivoPorReclamo;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (reclamosProcesados * incentivoPorReclamo);
    }

    public void cerrarReclamo() {
        reclamosProcesados++;
    }

    public int getReclamosProcesados() {
        return reclamosProcesados;
    }

    @Override
    public String toString() {
        return super.toString() + " | Reclamos procesados: " + reclamosProcesados;
    }
}
