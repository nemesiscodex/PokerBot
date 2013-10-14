/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

import evaluador.Deck;
import evaluador.Hand;
import evaluador.Probabilidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public abstract class Jugador {
    
    protected int pNumber;
    public boolean ronda;
    protected int dinero;
    protected Game parent;
    protected PlayerStatus status;
    public int getMaximaApuesta(){
        return Math.min(Math.min(parent.j1.status.getDinero(),parent.j2.status.getDinero()),200);
    }
    public PlayerStatus getOtherStatus(){
        if(pNumber==0)
            return parent.j2.status;
        return parent.j1.status;
    }
    
    /**
     * @param dinero
     * @param probabilidad
     * @param apuesta
     * @param obligada
     * @param border 
     */
    public void turno(boolean t){
        
        double p;
        if(t){
            long inicio, fin;
            inicio = System.currentTimeMillis();
            p = obtenerProbabilidad();
            fin = System.currentTimeMillis();
            parent.log("Tiempo: "+(fin-inicio)+" ms");
            status.setProbabilidad(p);
            parent.log(""+p);
            parent.actual = this;
            //siguienteAccion();
        }
    }
    public double obtenerProbabilidad(){//Falta implementar.
        Deck com;
        Hand mano;
        mano = new Hand(parent.juego[(pNumber*2)+0].toCard().toString()+" "+parent.juego[(pNumber*2)+1].toCard().toString());
        switch(parent.gameControl){
            case 0:
                return Probabilidad.getProbabilidadAntesDelFlop(mano);
            case 3:
            case 2:
            case 1:
                com = new Deck();
                mano = new Hand(mano.toString()+" "+parent.getVisibleMesaString());
                com.extractHand(mano);
                parent.log("Comunitarias "+parent.getVisibleMesaString());
                parent.log("Hand "+mano.toString());
                return Probabilidad.getProbabilidadFlop(mano, new Hand(parent.getVisibleMesaString()), com);
            
                
        }
        return 0.0;
    }
    public void Actualizar(Integer dinero, Double probabilidad, Integer apuesta, Integer obligada, Boolean border) {
        if (apuesta != null) {
            status.setApuesta(apuesta);
        }
        if (border != null) {
            status.setBorder(border);
        }
        if (probabilidad != null) {
            status.setProbabilidad(probabilidad);
        }
        if (obligada != null) {
            status.setObligada(obligada);
        }
        if (dinero != null) {
            status.setDinero(dinero);
        }
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }
    public void prop(){
        this.dinero = new Integer(this.status.getJtDinero().getText());
    }
    public Jugador(Game parent, int dinero, int playerNumber) {
        this.dinero = dinero;
        this.parent = parent;
        pNumber = playerNumber;
        if(pNumber == 0)
            this.status = this.parent.getPlayerStatus1();
        else 
            this.status = this.parent.getPlayerStatus2();
        this.status.getJtDinero().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent pce) {
                prop();
            }
        }); 
        this.status.getJbtnPasar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                igualar();
            }
        });
        this.status.getJbtnRetirarse().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                retirarse();
            }
        });
        
    }
    public int getDinero() {
        return dinero;
    }
    //@Override
    public void igualar() {
        int apuesta = status.getApuesta();
        
        apostar(apuesta);
        parent.log("Apuesta: "+apuesta+" Dinero: "+getDinero());
        status.setApuesta(0);
        if(apuesta!=0){
            if(parent.apuesta==0){
                parent.apuesta = apuesta;
                getOtherStatus().setApuesta(apuesta);
            }else{
                parent.apuesta = 0;
                getOtherStatus().setApuesta(0);
            }
        }
        status.setObligada(0);
        parent.turno = !parent.turno;
        parent.GameControl();
        
    }
    public abstract void subir(int i);
       // @Override
    public void retirarse() {
        JOptionPane.showMessageDialog(parent, "El Jugador "+pNumber+" se ha retirado.", "Fin de la ronda.", JOptionPane.INFORMATION_MESSAGE);
        apostar(status.getObligada());
        if(pNumber==0)
            parent.j2.ganaApuesta();
        else
            parent.j1.ganaApuesta();
        parent.nuevaRonda();
    }
    public void ganaApuesta() {
        parent.log("Gana Apuesta = "+dinero);
        dinero += parent.getPozo();
        status.setDinero(dinero);
        parent.pozo(0);
    }

    public abstract int optenerApuesta();

    public void apostar(int cantidad) {
        assert (cantidad <= dinero);
        parent.log("Player "+pNumber+" aposto "+cantidad);
        parent.spozo(cantidad);
        status.setDinero(status.getDinero()-cantidad);
        parent.log("Dinero luego de apostar: "+dinero);
        dinero = status.getDinero();
    }

    public abstract int siguienteAccion();
}
