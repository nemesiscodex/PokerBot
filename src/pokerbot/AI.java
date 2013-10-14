/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class AI extends Jugador {

    private double x, q;
    private boolean mentir;
    private int acumulado;

    public AI(Game parent, int dinero, int st) {
        super(parent, dinero, st);
        this.status.setVisibleButton(false);
        this.status.setVisiblePanelIA(true);
        this.status.setNombre("AI");

    }

    @Override
    public int obtenerApuesta() {
        int min = status.getObligada();
        int max = 200;
        return min;
    }

    @Override
    public int siguienteAccion() {
        parent.log("**Turno Player " + pNumber + "**");

        if (parent.gameControl == 0) {
            try {
                x = Double.parseDouble(status.getJtX());
                q = Double.parseDouble(status.getJtQ());
            } catch (NumberFormatException e) {
                x = 0.5;
                q = 0.75;
            }
            double rand = Math.random();
            mentir = (rand > q);
            parent.log("Random: " + rand + " q: " + q);

            if (mentir) {
                parent.log("Estrategia Mentirosa. Player " + pNumber);
            }
            acumulado = 0;
        }
        if (mentir) {
            estrategiaMentirosa();
        } else {
            estrategiaProbabilistica();
        }
        return -1;
    }

    @Override
    public void turno(boolean t) {
        super.turno(t);
        status.setBorder(t);
    }
    @Override
    public void apostar(int cantidad) {
        assert (cantidad <= dinero);
        acumulado+=cantidad;
        parent.log("Player "+pNumber+" aposto "+cantidad);
        parent.spozo(cantidad);
        status.setDinero(status.getDinero()-cantidad);
        parent.log("Dinero luego de apostar: "+dinero);
        dinero = status.getDinero();
    }
    @Override
    public void igualar() {
        super.igualar();
    }

    @Override
    public void subir(int i) {
        //parent.apuesta = i - parent.apuesta;
        status.setApuesta(i);
    }

    private void estrategiaMentirosa() {
        int apuesta;
        if(parent.gameControl==3 && status.getApuesta()>100)
            estrategiaProbabilistica();
        if(acumulado+status.getApuesta()<=600){//limite es 800, 600 es el 75% del limite
            apuesta = getMaximaApuesta();
//            if(status.getApuesta() == 0)
//                parent.mensaje("Player " + (pNumber + 1) + " Pasa.");
//            else
//                parent.mensaje("Player " + (pNumber + 1) + " Apuesta "+ apuesta);
            subir(apuesta);
            igualar();
            
        }else if(status.getApuesta()==0){
            igualar();
//            parent.mensaje("Player " + (pNumber + 1) + " Pasa.");
        }else{
            estrategiaProbabilistica();
        }
    }

    private void estrategiaProbabilistica() {
        double p = status.getProbabilidad() / 100.0;
        String jugada = "";
        parent.log("p: " + p + " x: " + x);
        if (p < x - .1) {
            if (status.getApuesta() == 0) {
                igualar();
                jugada = "Pasa.";
            } else {
                retirarse();
                jugada = "Se retira.";
            }
        } else if (p <= x + .2) {
            igualar();
            if (status.getApuesta() == 0) {
                jugada = "Pasa.";
            } else {
                jugada = "Iguala.";
            }
        } else {
            int cant = 0;
            double inc = (1 - x) / 2;
            if (p < x + inc) {
                cant = 100;
            } else{
                cant = 200;
            }
            cant = Math.min(cant, getMaximaApuesta());
            cant = Math.max(cant, parent.apuesta);
            if (cant == status.getApuesta()) {
                jugada = "Iguala.";
            } else {
                jugada = "Apuesta " + cant;
            }
            subir(cant);
            igualar();
        }
        if (parent.turno == parent.ciega || parent.gameControl != 0) {
            //parent.mensaje("Player " + (pNumber + 1) + " " + jugada);
        }
    }
}
