public class AgenteVentas extends Empleado {
    private int polizasVendidas;
    private double comisionPorVenta;

    public AgenteVentas(String nombre, int experiencia, double salarioBase, int polizasVendidas, double comisionPorVenta) {
        super(nombre, "Ventas", experiencia, salarioBase);
        this.polizasVendidas = polizasVendidas;
        this.comisionPorVenta = comisionPorVenta;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (polizasVendidas * comisionPorVenta);
    }

    public int getPolizasVendidas() {
        return polizasVendidas;
    }

    public void setPolizasVendidas(int polizasVendidas) {
        this.polizasVendidas = polizasVendidas;
    }

    public double getComisionPorVenta() {
        return comisionPorVenta;
    }

    public void setComisionPorVenta(double comisionPorVenta) {
        this.comisionPorVenta = comisionPorVenta;
    }

    @Override
    public String toString() {
        return super.toString() + " | Polizas vendidas: " + polizasVendidas;
    }
}
