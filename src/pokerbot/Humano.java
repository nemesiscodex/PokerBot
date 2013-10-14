/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class Humano extends Jugador{
    @Override
    public int siguienteAccion() {
        parent.log("**Turno Player "+pNumber+"**");
        return -1;
    }

    public Humano(Game parent,int dinero, int playerNumber) {
        super(parent,dinero,playerNumber);
        this.status.setVisiblePanelIA(false);
        this.status.setNombre("Humano");
    }

    @Override
    public int obtenerApuesta() {
        int ret;
        try{
            ret =Integer.parseInt((String)JOptionPane.showInputDialog("Cantidad"));
        }catch(NumberFormatException e){
            ret = obtenerApuesta();
        }
        if(getMaximaApuesta()<ret){
            ret = getMaximaApuesta();
        }
        apostar(ret);
        return ret;
    }

    @Override
    public void turno(boolean t) {
        super.turno(t);
        status.setBorder(t);
        status.setVisibleButton(t);
    }
    


    @Override
    public void subir(int subir) {
        parent.log("Apuesta actual: "+parent.apuesta);
    }


}
