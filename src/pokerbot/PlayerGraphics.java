/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

import javax.swing.JLabel;

/**
 *
 * @author Julio
 */
public class PlayerGraphics extends javax.swing.JPanel {

    /**
     * Creates new form PlayerGraphics
     */
    public PlayerGraphics() {
        initComponents();
    }

    public JLabel getCardGraphic1() {
        return cardGraphic1;
    }

    public void setCardGraphic1(JLabel cardGraphic1) {
        this.cardGraphic1 = cardGraphic1;
    }

    public JLabel getCardGraphic2() {
        return cardGraphic2;
    }

    public void setCardGraphic2(JLabel cardGraphic2) {
        this.cardGraphic2 = cardGraphic2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card1 = new javax.swing.JPanel();
        cardGraphic1 = new javax.swing.JLabel();
        cardGraphic2 = new javax.swing.JLabel();

        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(null);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        card1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        card1.setMaximumSize(null);
        card1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cardGraphic1.setMaximumSize(null);
        cardGraphic1.setMinimumSize(null);
        cardGraphic1.setPreferredSize(null);
        card1.add(cardGraphic1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 161, 220));

        cardGraphic2.setMaximumSize(null);
        cardGraphic2.setMinimumSize(null);
        cardGraphic2.setPreferredSize(null);
        card1.add(cardGraphic2, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 0, 161, 220));

        add(card1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 323, 220));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel card1;
    private javax.swing.JLabel cardGraphic1;
    private javax.swing.JLabel cardGraphic2;
    // End of variables declaration//GEN-END:variables
}
