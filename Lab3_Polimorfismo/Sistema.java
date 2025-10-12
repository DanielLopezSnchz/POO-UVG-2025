import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Empleado> empleados;
    private List<Poliza> polizas;
    private List<Reporte> reportes;
    private List<String> historial;

    public Sistema() {
        this.empleados = new ArrayList<>();
        this.polizas = new ArrayList<>();
        this.reportes = new ArrayList<>();
        this.historial = new ArrayList<>();
    }

    // MÉTODOS DE REGISTRO Y GESTIÓN 

    // Estos tres metodos no estaban considerados en el UML, pero son importantes para agregar empleados, polizas y reportes a la BD del sistema.

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
        log("Empleado agregado: " + e.getNombre() + " (" + e.getClass().getSimpleName() + ")");
    }

    public void agregarPoliza(Poliza p) {
        polizas.add(p);
        log("Póliza creada: " + p.getId() + " para cliente " + p.getNombreCliente());
    }

    public void agregarReporte(Reporte r) {
        reportes.add(r);
        log("Reporte generado: " + r.getId());
    }

    // ASIGNACIÓN Y OPERACIONES

    public Empleado asignarPoliza(Poliza poliza) {
        // Buscar el primer agente disponible
        for (Empleado e : empleados) {
            if (e instanceof AgenteVentas && e.isDisponible()) {
                poliza.asignarAgente(e);
                e.setDisponible(false);
                log("Póliza " + poliza.getId() + " asignada a " + e.getNombre());
                return e; // detenernos al asignar el primero disponible
            }
        }

        // Si no se encontró ninguno
        log("No hay agentes disponibles para la póliza " + poliza.getId());
        return null;
    }

    public void actualizarEstadoPoliza(Poliza poliza, EstadoPoliza nuevoEstado) {
        EstadoPoliza estadoAnterior = poliza.getEstado();
        poliza.setEstado(nuevoEstado);
        log("Estado de la póliza " + poliza.getId() + 
            " actualizado de " + estadoAnterior + " a " + nuevoEstado);
    }

    public List<AgenteVentas> mostrarAgentesDisponibles() {
        List<AgenteVentas> disponibles = new ArrayList<>();

        for (Empleado e : empleados) {
            if (e instanceof AgenteVentas && e.isDisponible()) {
                disponibles.add((AgenteVentas) e);
            }
        }

        if (disponibles.isEmpty()) {
            log("No hay agentes disponibles actualmente.");
        } else {
            log("Agentes disponibles encontrados: " + disponibles.size());
            for (AgenteVentas a : disponibles) {
                System.out.println("- " + a.getNombre());
            }
        }

        return disponibles;
    }



    // SINIESTROS Y DUPLICADOS

    public void detectarDuplicados() {
        List<String> vistos = new ArrayList<>();

        for (Poliza p : polizas) {
            for (Siniestro s : p.getSiniestros()) {
                String clave = (s.getTitulo().trim().toLowerCase() + s.getDescripcion().trim().toLowerCase());
                if (vistos.contains(clave)) {
                    log("Duplicado detectado: " + s.getTitulo() + " en póliza " + p.getId());
                } else {
                    vistos.add(clave);
                }
            }
        }
    }

    public void eliminarDuplicados() {
        List<String> vistos = new ArrayList<>();

        for (Poliza p : polizas) {
            List<Siniestro> filtrados = new ArrayList<>();
            for (Siniestro s : p.getSiniestros()) {
                String clave = (s.getTitulo().trim().toLowerCase() + s.getDescripcion().trim().toLowerCase());
                if (!vistos.contains(clave)) {
                    vistos.add(clave);
                    filtrados.add(s);
                } else {
                    log("Siniestro duplicado eliminado en póliza " + p.getId() + ": " + s.getTitulo());
                }
            }
            p.getSiniestros().clear();
            p.getSiniestros().addAll(filtrados);
        }
    }

    // REPORTES Y NÓMINA

    public Reporte generarReporteNomina() {
        Reporte r = new Reporte(TipoSeguro.EMPRESARIAL, true, 0);

        for (Empleado e : empleados) {
            r.agregarEmpleado(e);
        }

        reportes.add(r);
        log("Reporte de nómina generado con " + empleados.size() + " empleados.");

        return r; // solo devuelve el modelo del reporte
    }
    

    // HISTORIAL Y UTILIDADES

    private void log(String mensaje) { // esta funcion sirve para registrar todos los mensajes de las actividades hechas por los otros metodos en Sistema y guardarlos en el historial.
        String entrada = "[" + LocalDateTime.now() + "] " + mensaje;
        historial.add(entrada);
        System.out.println(entrada);
    }

    public String getUltimaNotificacion() {
        if (historial.isEmpty()) return "Sin notificaciones.";
        return historial.get(historial.size() - 1);
    }

    public List<String> getHistorial() {
        return historial;
    }

    public List<Poliza> getPolizas(){
        return this.polizas;
    }

}
