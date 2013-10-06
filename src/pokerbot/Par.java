/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

import java.util.Objects;

/**
 *
 * @author Julio
 */

public class Par {
    Carta mayor;
    Carta menor;
    final double m[][] = {{},{},{},{},{},{},{},{},{},{},{},{},{}};
    
    double preFlop(){ //
        return 0.0;
    }
    Par(Carta a, Carta b){
        if(a.compareTo(b)>0){
            mayor = a;
            menor = b;
        }else{
            mayor = b;
            menor = a;
        }
    }

    public Carta getMayor() {
        return mayor;
    }

    public void setMayor(Carta mayor) {
        this.mayor = mayor;
    }

    public Carta getMenor() {
        return menor;
    }

    public void setMenor(Carta menor) {
        this.menor = menor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.mayor);
        hash = 97 * hash + Objects.hashCode(this.menor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Par other = (Par) obj;
        if (!Objects.equals(this.mayor, other.mayor)) {
            return false;
        }
        if (!Objects.equals(this.menor, other.menor)) {
            return false;
        }
        return true;
    }
    
}
