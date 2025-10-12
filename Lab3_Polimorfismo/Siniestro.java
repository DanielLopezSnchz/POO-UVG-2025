import java.time.LocalDateTime;
import java.util.UUID;

public class Siniestro {
    private String id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha; // Atributo nuevo, no incluido en el UML

    public Siniestro(String titulo, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Siniestro {" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
