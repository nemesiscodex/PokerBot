/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

/**
 *
 * @author Julio
 */
public abstract class Jugador {
    public static int maximaApuesta; //maxima apuesta
    public static int minima;
    public boolean ronda;
    protected int dinero;

    public Jugador(int dinero) {
        this.dinero = dinero;
    }

    public int getDinero() {
        return dinero;
    }
    
    public void ganaApuesta(int pozo){
        dinero+=pozo;
    }
    
    public abstract int optenerApuesta();
    public void apostar(int cantidad){
        assert(cantidad<=dinero);
        dinero-=cantidad;
    }
    public abstract int siguienteAccion();
}
