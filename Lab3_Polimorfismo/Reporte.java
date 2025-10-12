import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Reporte {
    private String id;
    private TipoSeguro tipoSeguro;
    private boolean aprobado;
    private double bonoEficiencia;
    private List<Empleado> empleadosInvolucrados;
    private LocalDateTime fechaGeneracion;

    // Constructor
    public Reporte(TipoSeguro tipoSeguro, boolean aprobado, double bonoEficiencia) {
        this.id = UUID.randomUUID().toString();
        this.tipoSeguro = tipoSeguro;
        this.aprobado = aprobado;
        this.bonoEficiencia = bonoEficiencia;
        this.empleadosInvolucrados = new ArrayList<>();
        this.fechaGeneracion = LocalDateTime.now();
    }

    // Métodos
    public void agregarEmpleado(Empleado empleado) {
        empleadosInvolucrados.add(empleado);
    }

    public void mostrarReporte() {
        System.out.println("==== REPORTE ====");
        System.out.println("ID: " + id);
        System.out.println("Tipo de seguro: " + tipoSeguro);
        System.out.println("Aprobado: " + (aprobado ? "Sí" : "No"));
        System.out.println("Bono eficiencia: Q" + bonoEficiencia);
        System.out.println("Fecha de generación: " + fechaGeneracion);
        System.out.println("Empleados involucrados:");
        for (Empleado e : empleadosInvolucrados) {
            System.out.println(" - " + e.getNombre() + " (" + e.getClass().getSimpleName() + ")");
        }
        System.out.println("=================");
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public double getBonoEficiencia() {
        return bonoEficiencia;
    }

    public List<Empleado> getEmpleadosInvolucrados() {
        return empleadosInvolucrados;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    @Override
    public String toString() {
        return "Reporte {" +
                "id='" + id + '\'' +
                ", tipoSeguro=" + tipoSeguro +
                ", aprobado=" + aprobado +
                ", bonoEficiencia=" + bonoEficiencia +
                ", empleadosInvolucrados=" + empleadosInvolucrados.size() +
                ", fechaGeneracion=" + fechaGeneracion +
                '}';
    }
}
