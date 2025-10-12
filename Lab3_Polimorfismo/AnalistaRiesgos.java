import java.util.List;

public class AnalistaRiesgos extends Empleado {
    private List<TipoSeguro> tiposPermitidos;
    private int reportesEvaluados;
    private double bonoPorReporte;

    public AnalistaRiesgos(String nombre, int experiencia, double salarioBase,
                           List<TipoSeguro> tiposPermitidos, int reportesEvaluados, double bonoPorReporte) {
        super(nombre, "Administración", experiencia, salarioBase);
        this.tiposPermitidos = tiposPermitidos;
        this.reportesEvaluados = reportesEvaluados;
        this.bonoPorReporte = bonoPorReporte;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (reportesEvaluados * bonoPorReporte);
    }

    public List<TipoSeguro> getTiposPermitidos() {
        return tiposPermitidos;
    }

    public int getReportesEvaluados() {
        return reportesEvaluados;
    }

    public void setReportesEvaluados(int reportesEvaluados) {
        this.reportesEvaluados = reportesEvaluados;
    }

    @Override
    public String toString() {
        return super.toString() + " | Reportes evaluados: " + reportesEvaluados;
    }
}
