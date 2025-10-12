public class EjecutivoAtencion extends Empleado {
    private int clientesAsignados;
    private double indiceSatisfaccion; // valor de 0 a 100

    public EjecutivoAtencion(String nombre, int experiencia, double salarioBase,
                             int clientesAsignados, double indiceSatisfaccion) {
        super(nombre, "Atención", experiencia, salarioBase);
        this.clientesAsignados = clientesAsignados;
        this.indiceSatisfaccion = indiceSatisfaccion;
    }

    @Override
    public double calcularSalario() {
        double bono = (clientesAsignados * (indiceSatisfaccion / 100) * 10); // Formula ideada para calcular el bono por satisfaccion.
        return salarioBase + bono;
    }

    public int getClientesAsignados() {
        return clientesAsignados;
    }

    public double getIndiceSatisfaccion() {
        return indiceSatisfaccion;
    }

    @Override
    public String toString() {
        return super.toString() + " | Clientes: " + clientesAsignados +
               " | Satisfacción: " + indiceSatisfaccion + "%";
    }
}
