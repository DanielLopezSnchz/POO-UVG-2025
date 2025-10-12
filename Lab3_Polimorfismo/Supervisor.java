import java.util.List;

public class Supervisor extends Empleado {
    private List<Empleado> equipoTrabajo;
    private double bonoDesempeno;

    public Supervisor(String nombre, int experiencia, double salarioBase,
                      List<Empleado> equipoTrabajo, double bonoDesempeno) {
        super(nombre, "Administración", experiencia, salarioBase);
        this.equipoTrabajo = equipoTrabajo;
        this.bonoDesempeno = bonoDesempeno;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + bonoDesempeno;
    }

    public List<Empleado> getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public double getBonoDesempeno() {
        return bonoDesempeno;
    }

    @Override
    public String toString() {
        return super.toString() + " | Equipo a cargo: " +
               (equipoTrabajo != null ? equipoTrabajo.size() : 0);
    }
}
