/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

import evaluador.Card;

/**
 *
 * @author Julio
 */
public class Carta implements Comparable<Carta> {

    int valor;
    int mazo;
    int index;
    final static int CORAZONES = 0;
    final static int PICAS = 1;
    final static int DIAMANTES = 2;
    final static int TREBOLES = 3;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Carta(int mazo, int valor) {
        this.valor = valor;
        this.mazo = mazo;
    }

    public Carta(int id) {
        this.index = id;
        this.valor = id % 13;
        this.mazo = id / 13;
    }

    public int getValor() {
        return valor;
    }

    public Card toCard() {
        int rank,suit;
        switch(mazo){
            case CORAZONES:
                suit = 2;
                break;
            case DIAMANTES:
                suit = 1;
                break;
            case PICAS:
                suit = 3;
                break;
            case TREBOLES:
                suit = 0;
                break;
            default:
                suit = -1;
        }
        rank = valor - 1;
        if(rank==-1)
            rank = 12;
        return new Card(rank, suit);
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getMazo() {
        return mazo;
    }

    public void setMazo(int mazo) {
        this.mazo = mazo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.valor;
        hash = 37 * hash + this.mazo;
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
        final Carta other = (Carta) obj;
        if (this.valor != other.valor) {
            return false;
        }
        if (this.mazo != other.mazo) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Carta t) {
        int a = this.valor;
        int b = t.valor;
        if (a == 1) {
            a = 14;
        }
        if (b == 1) {
            b = 14;
        }
        return a - b;
    }
}
