/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utileria;

import java.util.ArrayList;
import pokerbot.Carta;

/**
 *
 * @author synchro
 */
public class Mazo {

    private static ArrayList universo;
    private static ArrayList conjuntoActual;
    private static ArrayList conocidas;
    private static int totalTomado;

    public static ArrayList combinacion(ArrayList u, Carta[] cartas, int mostradas) {
        universo = u;
        int tomado = 7;
        if (mostradas == 3) {
            tomado = 4;
        }
        if (mostradas == 4) {
            tomado = 3;
        }
        if (mostradas == 5) {
            tomado = 2;
        }
        totalTomado = tomado;
        conjuntoActual = new ArrayList();
        conocidas = new ArrayList();
        conocidas.add(cartas[0].getIndex());
        conocidas.add(cartas[1].getIndex());

        switch (mostradas) {
            case 5:
                conocidas.add(cartas[8].getIndex());
            case 4:
                conocidas.add(cartas[7].getIndex());
            case 3:
                conocidas.add(cartas[4].getIndex());
                conocidas.add(cartas[5].getIndex());
                conocidas.add(cartas[6].getIndex());
                break;
            default:
                System.err.println("!!!! " + mostradas);
                break;
        }

        for (int i = 0; i < totalTomado; i++) {
            conjuntoActual.add((Integer) (-1));
        }

        return combinar(0, tomado);
    }

    public static ArrayList combinar(int elementoActual, int tomado) {
        ArrayList res = new ArrayList();
        for (int i = elementoActual; i <= universo.size() - tomado; i++) {
            if (!isIn(conocidas, i)) {
                conjuntoActual.set(totalTomado - tomado, universo.get(i));
                if (tomado > 1) {
                    res.addAll(combinar(i + 1, tomado - 1));
                } else {
                    res.add((ArrayList) conjuntoActual.clone());
                }
            }
        }
        return res;
    }

    public static void combinar(ArrayList resultado, ArrayList conjuntoActual,
            ArrayList universo, int elementoActual, int tomado) {
        for (int i = elementoActual; i <= universo.size() - tomado; i++) {
            ArrayList temp = (ArrayList) conjuntoActual.clone();
            temp.add(universo.get(i));

            if (tomado == 1) {
                resultado.add((ArrayList) temp.clone());
            } else {
                combinar(resultado, temp, universo, i + 1, tomado - 1);
            }
            temp.clear();
        }
    }

    private static boolean isIn(ArrayList conocidas, int i) {
        boolean res = false;
        for (int j = 0; j < conocidas.size(); j++) {
            if (i == (int) conocidas.get(j)) {
                System.out.println("DEBUG> CARTA " + i);
                res = true;
            }
        }
        return res;
    }
}
