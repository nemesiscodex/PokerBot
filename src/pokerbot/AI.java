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
public class AI extends Jugador{
    private double x,q;
    private boolean mentir;
    private int acumulado;
    
    public AI(Game parent, int dinero, int st) {
        super(parent, dinero, st);
        this.status.setVisibleButton(false);
        this.status.setVisiblePanelIA(true);
        this.status.setNombre("AI");
        
    }

    @Override
    public int optenerApuesta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int siguienteAccion() {
        parent.log("**Turno Player "+pNumber+"**");
        
        if(parent.gameControl==0){
            try{
            x = Double.parseDouble(status.getJtX());
            q = Double.parseDouble(status.getJtQ());
            }catch(NumberFormatException e){
                x = 0.5;
                q = 0.75;
            }
            mentir = (Math.random()>q);
            if(mentir){
                parent.log("Estrategia Mentirosa. Player "+pNumber);
            }
            acumulado = 0;
        }
        if(mentir){
            estrategiaMentirosa();
        }else{
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
    public void igualar(){
        super.igualar();
        //if(parent.gameControl!=0 || parent.turno==!parent.ciega)
        parent.log(parent.turno+" "+parent.ciega);
        if(parent.turno==parent.ciega || parent.gameControl!=0)
        parent.mensaje("Player "+(pNumber+1)+" Iguala.");
        
    }

    @Override
    public void subir(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void retirarse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void estrategiaMentirosa() {
        igualar();
    }

    private void estrategiaProbabilistica() {
        igualar();
    }
    
}

