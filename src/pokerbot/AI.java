/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

/**
 *
 * @author Julio
 */
public class AI extends Jugador{
    private double x,q;
    private boolean mentir;
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
        if(parent.gameControl==0){
            x = Double.parseDouble(status.getJtX());
            q = Double.parseDouble(status.getJtQ());
            mentir = (Math.random()>q);
            if(mentir){
                parent.log("Estrategia Mentirosa. Player "+pNumber);
            }
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
    public void igualar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void estrategiaProbabilistica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

