/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import pokerbot.Carta;
import utileria.Mazo;

/**
 *
 * @author synchro
 */
public class Test {
    public static void main(String args[])
    {
        int TOTAL = 52;//Constante
        int MOSTRADO = 5;// 3 flop, 4 turn, 5 river
        
        ArrayList baraja = new ArrayList();
        
        for(int i = 0; i < TOTAL; i++)
        {
            baraja.add(i,(Integer)i);
        }
        
        System.out.println("Combinacion de "+TOTAL+" tomados de a "+ (7 - MOSTRADO));
        Long t0 = System.nanoTime();
        Carta show[] = new Carta[9];
        show[0] = new Carta(4);
        show[1] = new Carta(13);
        show[2] = new Carta(51);
        show[3] = new Carta(1);
        show[4] = new Carta(6);
        show[5] = new Carta(7);
        show[6] = new Carta(26);
        show[7] = new Carta(25);
        show[8] = new Carta(39);
        
        ArrayList resultado = Mazo.combinacion(baraja,show,MOSTRADO);
        Long t1= System.nanoTime();
        
        for(int i = 0; i < resultado.size(); i++)
        {
            ArrayList temp = (ArrayList)resultado.get(i);
            System.out.print("[ ");
            for(int j = 0; j < temp.size(); j++)
            {
                System.out.print(temp.get(j)+" ");
            }
            System.out.println("]");
        }
        System.out.println("Delta T-> "+ (t1-t0) +" [ns]");

        System.out.println("Existen "+resultado.size()+" combinaciones posibles");
    }
    
}
