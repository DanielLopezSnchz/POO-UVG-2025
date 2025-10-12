import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Poliza {
    private String id;
    private String nombreCliente;
    private TipoSeguro tipoSeguro;
    private EstadoPoliza estado;
    private Prioridad prioridad;
    private Empleado agenteResponsable;
    private List<Siniestro> siniestros;

    public Poliza(String nombreCliente, TipoSeguro tipoSeguro) {
        this.id = UUID.randomUUID().toString();
        this.nombreCliente = nombreCliente;
        this.tipoSeguro = tipoSeguro;
        this.estado = EstadoPoliza.PENDIENTE;
        this.prioridad = Prioridad.MEDIA;
        this.siniestros = new ArrayList<>();
    }

    // Métodos
    public void asignarAgente(Empleado agente) {
        this.agenteResponsable = agente;
    }

    public void registrarSiniestro(Siniestro s) {
        siniestros.add(s);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public EstadoPoliza getEstado() {
        return estado;
    }

    public void setEstado(EstadoPoliza estado) {
        this.estado = estado;
    }

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public Empleado getAgenteResponsable() {
        return agenteResponsable;
    }

    @Override
    public String toString() {
        return "Poliza {" +
                "id='" + id + '\'' +
                ", cliente='" + nombreCliente + '\'' +
                ", tipoSeguro='" + tipoSeguro + '\'' +
                ", estado='" + estado + '\'' +
                ", prioridad='" + prioridad + '\'' +
                ", agenteResponsable=" + (agenteResponsable != null ? agenteResponsable.getNombre() : "Ninguno") +
                ", siniestros=" + siniestros.size() +
                '}';
    }
}
