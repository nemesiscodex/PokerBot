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
public class Humano extends Jugador{

    @Override
    public int siguienteAccion() {
        Object []opciones = {"Apostar","Pasar","Retirarse"};
        return JOptionPane.showOptionDialog(null, "Que desea hacer?", "Siguiente Accion.", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);
    }

    public Humano(int dinero) {
        super(dinero);
    }

    @Override
    public int optenerApuesta() {
        int ret;
        try{
            ret =Integer.parseInt((String)JOptionPane.showInputDialog("Cantidad"));
        }catch(NumberFormatException e){
            ret = optenerApuesta();
        }
        if(maximaApuesta<ret){
            ret = maximaApuesta;
        }
        apostar(ret);
        return ret;
    }
    public static void main(String []args){
        System.out.println(new Humano(100).optenerApuesta());
    }
}
