import java.util.UUID;

public abstract class Empleado {
    // Atributos comunes
    protected String id;
    protected String nombre;
    protected String departamento;
    protected int experiencia;       // en años
    protected double salarioBase;
    protected boolean disponible;

    // Constructor
    public Empleado(String nombre, String departamento, int experiencia, double salarioBase) {
        this.id = UUID.randomUUID().toString(); // Esta funcion del paquete UUID es util para generar codigos de identificacion a traves de todos los sistemas de un programa.
        this.nombre = nombre;
        this.departamento = departamento;
        this.experiencia = experiencia;
        this.salarioBase = salarioBase;
        this.disponible = true;
    }

    // Método abstracto (polimórfico)
    public abstract double calcularSalario();

    // Métodos getters y setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", departamento='" + departamento + '\'' +
                ", experiencia=" + experiencia +
                ", salarioBase=" + salarioBase +
                ", disponible=" + disponible +
                '}';
    }
}
