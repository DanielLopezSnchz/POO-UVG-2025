public class Tablero {
    // Atributo
    private Celda[][] celdas;

    // Constructor
    public Tablero(){
        celdas = new Celda[3][3];
        for(int r = 0; r<3; r++){
            for (int c = 0; c < 3; c++){
                celdas[r][c] = new Celda();
            }
        }
    }

    // Metodos

    public Celda getCelda(int r, int c){
        return celdas[r][c];
    }

    public boolean marcar(Marca m, int r, int c){
        if (r<0 || r>2 || c<0 || c>2) return false; // fuera de rango
        if (!celdas[r][c].estaVacia()) return false;
        celdas[r][c].setMarca(m);
        return true;
    }

    public int celdarJugadas() {
        int count = 0;
        for(int r = 0; r<3; r++){
            for(int c = 0; c<3; c++){
                if(!celdas[r][c].estaVacia()){
                    count ++;
                }
            }
        }
        return count;
    }

    public boolean tableroLleno(){
        return celdarJugadas()==(3*3);
    }

    public void reiniciar(){
        for(int r=0; r<3; r++){
            for(int c=0; c<3; c++){
                celdas[r][c].setMarca(Marca.VACIA);
            }
        }
    }

    public Resultado comprobarGanador(){
        // Revisar filas
        for (int r = 0; r<3 ; r++){
            Marca a = celdas[r][0].getMarca();
            if(a != Marca.VACIA && a == celdas[r][1].getMarca() && a==celdas[r][2].getMarca()){
                return (a == Marca.CIRCULO) ? Resultado.CIRCULO : Resultado.EQUIS;
            }
        }

        // Revisar columnas
        for (int c = 0; c<3 ; c++){
            Marca a = celdas[0][c].getMarca();
            if(a != Marca.VACIA && a == celdas[1][c].getMarca() && a == celdas[2][c].getMarca()){
                return (a == Marca.CIRCULO) ? Resultado.CIRCULO : Resultado.EQUIS;
            }
        }

        // Revisar diagonal principal
        Marca a = celdas[0][0].getMarca();
        if (a != Marca.VACIA && a == celdas[1][1].getMarca() && a == celdas[2][2].getMarca()){
            return (a == Marca.CIRCULO) ? Resultado.CIRCULO : Resultado.EQUIS;
        }

        // Revisar daigonal secundaria
        a = celdas[0][2].getMarca();
        if (a != Marca.VACIA && a == celdas[1][1].getMarca() && a == celdas[2][0].getMarca()){
            return (a == Marca.CIRCULO) ? Resultado.CIRCULO : Resultado.EQUIS;
        }

        if (tableroLleno()){
            return Resultado.EMPATE;
        }

        return Resultado.NINGUNO; // Si no hay ganador. (aun)

    }


}
