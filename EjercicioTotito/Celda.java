public class Celda
{
    // Atributos
    private Marca marca;

    // Constructor
    public Celda()
    {
        this.marca = Marca.VACIA;
    }

    // Metodos
    public Marca getMarca(){
        return this.marca;
    }

    public boolean estaVacia(){
        return this.marca == Marca.VACIA;
    }

    public void setMarca(Marca m){
        this.marca = m;
    }

}