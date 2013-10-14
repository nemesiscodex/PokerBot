package evaluador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alberto
 */
public class Probabilidad {

  
    //private static PreFlop pf = new PreFlop();
    static double []  paresPreFlop= {49.3, 52.7, 55.9, 59.4, 62.6, 65.6, 68.7,
                                    71.4, 74.7, 77.0, 79.7, 82.4, 85.0};
    static double [][]  mismoPaloPreFlop= { {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {32.9, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {33.8, 36.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {35.2, 36.6, 38.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {34.8, 37.0, 38.4, 40.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {35.5, 37.7, 38.9, 40.8, 43.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {37.5, 38.2, 40.1, 42.1, 43.6, 45.8, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {40.0, 40.7, 41.5, 43.6, 45.0, 47.3, 48.9, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {42.4, 43.5, 44.0, 45.2, 46.6, 48.5, 50.7, 52.4, 0.0, 0.0, 0.0, 0.0, 0.0},
                                          {45.2, 46.1, 46.7, 47.9, 48.4, 50.4, 52.4, 54.1, 56.4, 0.0, 0.0, 0.0, 0.0},
                                          {48.0, 49.2, 50.1, 50.9, 51.7, 52.6, 54.1, 56.2, 58.5, 59.2, 0.0, 0.0, 0.0},
                                          {51.1, 51.8, 52.9, 53.8, 54.8, 55.8, 57.0, 58.5, 60.8, 61.2, 62.2, 0.0, 0.0},
                                          {55.5, 56.5, 57.5, 58.1, 58.4, 59.2, 60.3, 61.6, 63.3, 64.3, 65.6, 66.3, 0.0}};
  
    static double [][]  distintoPaloPreFlop= {  {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {29.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {30.3, 32.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {31.3, 33.1, 34.9, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {31.0, 32.8, 34.9, 36.9, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {31.5, 33.7, 35.8, 38.0, 39.6, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {34.1, 34.8, 36.8, 39.0, 40.8, 43.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {36.5, 37.4, 38.1, 39.9, 42.1, 44.1, 46.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {39.1, 40.5, 40.8, 41.7, 43.9, 46.0, 47.5, 49.7, 0.0, 0.0, 0.0, 0.0, 0.0},
                                              {42.1, 42.8, 43.5, 45.0, 45.8, 47.7, 50.0, 51.6, 53.8, 0.0, 0.0, 0.0, 0.0},
                                              {45.0, 45.7, 46.7, 48.0, 49.0, 49.9, 51.9, 53.9, 55.7, 57.1, 0.0, 0.0, 0.0},
                                              {48.4, 49.2, 40.3, 51.2, 52.3, 53.5, 54.4, 56.1, 58.3, 59.6, 60.7, 0.0, 0.0},
                                              {53.1, 53.8, 55.0, 55.8, 55.7, 57.4, 56.2, 59.4, 61.3, 62.5, 63.3, 64.4, 0.0}};

 /*public static double getProbabilidadPreFlop(Hand h1)
 {
   return ((Double)pf.getHashMap().get(h1.getHashValue())).doubleValue();
 }   
 */
    public static double getProbabilidadFlop(Hand h1, Hand h, Deck deck) {

        ArrayList<Card> set = new ArrayList<Card>();

        for(int i = deck.getTopCardIndex(); i < Deck.NUM_CARDS; i++) {
            set.add(deck.getCard(i));
        }

        System.out.println("Calulo probabilidad del flop.");

        //generar todas las combinaciones de 47 tomadas de a 4
        CombinationGenerator<Card> cg = new CombinationGenerator<Card>(set, 4);

        int total, favorables, empate, victoria, derrota;
        total = favorables = empate = victoria = derrota = 0;

        //para generar todas las combinaciones de 4 tomadas de a 2
        CombinationGenerator<Card> _cg;
        Hand _h1, _h2;
        HandEvaluator he = new HandEvaluator();

        for(List<Card> combination : cg) {

            _cg = new CombinationGenerator<Card>(combination, 2);

            for (List<Card> _combination: _cg) {

                total++; //total de combinaciones posibles

                //cartas para el jugador 1
                _h1 = new Hand(h1); //Se "clona" las cartas del jugador 1 (5 cartas)

                for (Card c: _combination){ //cartas de la mesa (2 cartas)
                    _h1.addCard(c);
                }

                //cartas para el jugador 2
                _h2 = new Hand(h);

                for (Card c: combination) { //las 4 de la combinacion
                    _h2.addCard(c);
                }

                switch (he.compareHands(_h1, _h2)) {
                    case 0:
                        empate++;
                        favorables++;
                        break;
                    case 1:
                        victoria++;
                        favorables++;
                        break;
                    case 2:
                        derrota++;
                        break;
                }
            }
        }
        
        return ((float)victoria / total); //probabilidad de ganar
    }

    public static double getProbabilidadTurn(Hand h1, Hand h, Deck deck) {

        ArrayList<Card> set = new ArrayList<Card>();

        for(int i = deck.getTopCardIndex(); i < Deck.NUM_CARDS; i++) {
            set.add(deck.getCard(i));
        }

        System.out.println("Calulo probabilidad del turn.");

        //generar todas las combinaciones de 46 tomadas de a 3
        CombinationGenerator<Card> cg = new CombinationGenerator<Card>(set, 3);

        int total, favorables, empate, victoria, derrota;
        total = favorables = empate = victoria = derrota = 0;

        //para generar todas las combinaciones de 3 tomadas de a 1
        CombinationGenerator<Card> _cg;
        Hand _h1, _h2;
        HandEvaluator he = new HandEvaluator();

        for(List<Card> combination : cg) {

            _cg = new CombinationGenerator<Card>(combination, 1);

            for (List<Card> _combination: _cg) {

                 total++; //total de combinaciones posibles

                //cartas para el jugador 1
                _h1 = new Hand(h1); //Se "clona" las cartas del jugador 1 (6 cartas)
                _h1.addCard(_combination.get(0)); //river

                //cartas para el jugador 2
                _h2 = new Hand(h);
                _h2.addCard(combination.get(0));
                _h2.addCard(combination.get(1));
                _h2.addCard(combination.get(2));

                switch (he.compareHands(_h1, _h2)) {
                    case 0:
                        empate++;
                        favorables++;
                        break;
                    case 1:
                        victoria++;
                        favorables++;
                        break;
                    case 2:
                        derrota++;
                        break;
                }
            }
        }

        return ((float)victoria / total); //probabilidad de ganar
    }

    public static double getProbabilidadRiver(Hand h1, Hand h, Deck deck) {

        ArrayList<Card> set = new ArrayList<Card>();

        for(int i = deck.getTopCardIndex(); i < Deck.NUM_CARDS; i++) {
            set.add(deck.getCard(i));
        }

        System.out.println("Calulo probabilidad del river.");

        //generar todas las combinaciones de 45 tomadas de a 2
        CombinationGenerator<Card> cg = new CombinationGenerator<Card>(set, 2);

        int total, favorables, empate, victoria, derrota;
        total = favorables = empate = victoria = derrota = 0;

        Hand _h1, _h2;
        HandEvaluator he = new HandEvaluator();

        for(List<Card> combination : cg) {

            total++; //total de combinaciones posibles

            //cartas para el jugador 1
            _h1 = new Hand(h1); //Se "clona" las cartas del jugador 1 (7 cartas)

            //cartas para el jugador 2
            _h2 = new Hand(h);
            _h2.addCard(combination.get(0));
            _h2.addCard(combination.get(1));

            switch (he.compareHands(_h1, _h2)) {
                case 0:
                    empate++;
                    favorables++;
                    break;
                case 1:
                    victoria++;
                    favorables++;
                    break;
                case 2:
                    derrota++;
                    break;
            }
        }

        return ((float)victoria / total);
    }
    
    public static double getProbabilidadAntesDelFlop(Hand mano) {
        int carta1;
        int carta2;
        int palo1;
        int palo2;
        float prob;
        System.out.println("Calulo probabilidad del pre-flop.");
        carta1= mano.getCardIndex(1);
        carta2= mano.getCardIndex(2);
        palo1= (int)(carta1/13);
        palo2= (int)(carta2/13);
        
        if(carta1%13 == carta2%13){
            prob= (float) (paresPreFlop[carta1%13]/100.0);
            return prob ;
        }else if(palo1 == palo2 ){
            if((int)(carta1%13) > (int)(carta2%13)){
                prob= (float)(mismoPaloPreFlop[carta1%13][carta2%13]/100.0);
                return prob;
            }
            prob= (float)(mismoPaloPreFlop[carta2%13][carta1%13]/100.0);
            return prob;

        }else{
            if((int)(carta1%13) > (int)(carta2%13)){
                prob= (float)(distintoPaloPreFlop[carta1%13][carta2%13]/100.0);
                return prob;
            }
            prob= (float)(distintoPaloPreFlop[carta2%13][carta1%13]/100.0);
            return prob;
        }

    }

}