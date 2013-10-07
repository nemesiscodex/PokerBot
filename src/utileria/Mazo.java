/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utileria;

import java.util.ArrayList;

/**
 *
 * @author synchro
 */
public class Mazo {
    public static void combinar(ArrayList resultado , ArrayList conjuntoActual,
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
}
