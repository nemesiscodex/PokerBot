/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerbot;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import evaluador.Hand;
import evaluador.HandEvaluator;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.BitSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class Game extends javax.swing.JFrame {

    Jugador j1;
    Jugador j2;
    Jugador actual;
    int gameControl;
    int apuesta;
    boolean ciega;
    boolean turno;
    BufferedImage deck, back;
    Carta[] juego;
    BitSet b;
    private String c1, c2, mesa;

    private void gameToHand() {
        //c1;
        c1 = juego[0].toCard().toString() + " " + juego[1].toCard().toString();
        c2 = juego[2].toCard().toString() + " " + juego[3].toCard().toString();
        mesa = "";
        for (int i = 4; i < 9; i++) {
            mesa += juego[i].toCard().toString() + " ";
        }
    }

    public void mensaje(String m) {
        JOptionPane.showMessageDialog(this, m);
    }

    void decidirGanador() {
        gameToHand();
        Hand h1, h2;
        h1 = new Hand(mesa + c1);
        h2 = new Hand(mesa + c2);
        int a, b;
        a = HandEvaluator.rankHand(h1);
        b = HandEvaluator.rankHand(h2);
        String n1, n2;
        n1 = HandEvaluator.nameHand(h1);
        n2 = HandEvaluator.nameHand(h2);
        if (a > b) {
            if (n1.equalsIgnoreCase(n2)) {
                n1 += " con carta Alta";
            }
            JOptionPane.showMessageDialog(this, "Gano Player 1\n" + n1);
            j1.ganaApuesta();
        } else if (a < b) {
            if (n1.equalsIgnoreCase(n2)) {
                n2 += " con carta Alta";
            }
            JOptionPane.showMessageDialog(this, "Gano Player 2\n" + n2);
            j2.ganaApuesta();
        } else {
            JOptionPane.showMessageDialog(this, "Empate\nPlayer 1: " + n1 + "\nPlayer 2: " + n2);
            int mitad = getPozo() / 2;
            j1.dinero += mitad;
            j2.dinero += mitad;
            jtPozo.setText("0");
        }

    }

    void log(String newLine) {
        String actual = jTextArea1.getText();
        jTextArea1.setText(actual + newLine + "\n");
    }
    public void focusJugar(){
        jugar.requestFocus();
    }
    void GameControl() {
        if (j1.status.getApuesta() == 0 && j2.status.getApuesta() == 0 && turno == !ciega) {
            gameControl++;
            mostrarCartas(gameControl);
            this.repaint();
        }
        
        if((j1.status.getDinero()==0 || j2.status.getDinero()==0) && j1.status.getApuesta() == 0 && j2.status.getApuesta() == 0){
            gameControl=4;
            mostrarCartas(gameControl);
        }
        if (gameControl == 4) {
            decidirGanador();
            if(j1.status.getDinero()==0 || j2.status.getDinero()==0){
                mensaje("Termino el Juego.");
                jMenuItem1ActionPerformed(null);
            }else{
                nuevaRonda();
            }
        } else {
            j1.turno(turno);
            j2.turno(!turno);
        }
        focusJugar();
    }

    void setObligada() {
        int a, c;
        if (ciega) {
            a = 100;
            c = 50;
        } else {
            a = 50;
            c = 100;
        }
        j1.Actualizar(null, null, 100, a, null);
        j2.Actualizar(null, null, 100, c, null);
    }

    public Integer getPozo() {
        return Integer.parseInt(jtPozo.getText());
    }

    public void pozo(Integer i) {
        jtPozo.setText(i.toString());
    }

    public void spozo(Integer i) {
        Integer x = Integer.parseInt(jtPozo.getText());
        jtPozo.setText(new Integer(x + i).toString());
    }

    void nuevaRonda() {
        log("*** NUEVA RONDA ***");
        setObligada();
        if(ciega){
            actual = j1;
        }else{
            actual = j2;
        }
        turno = ciega;
        ciega = !ciega;

        gameControl = 0;
        iniciarMesa();
        b = new BitSet(52);
        b.set(0, 51, false);
        Repartir();
        mostrarCartas(0);
        GameControl();
    }

    void nuevoJuego(Jugador j1, Jugador j2) {
        log("Juego Nuevo.");
        pozo(0);
        ciega = true;
        this.j1 = j1;
        this.j2 = j2;
        nuevaRonda();
    }

    /**
     * Creates new form Game
     */
    void Repartir() {
        log("Repartir.");
        juego = new Carta[9];
        Random r = new Random();
        int x;
        for (int i = 0; i < 9; i++) {
            while (b.get(x = Math.abs(r.nextInt() % 52)) == true);
            b.set(x, true);
            juego[i] = new Carta(x);
        }
        playerStatus1.setDinero(j1.getDinero());
        playerStatus2.setDinero(j2.getDinero());
    }

    void mostrarCartas(int i) {
        Carta c1, c2, c3, c4, c5;

        c1 = c2 = c3 = c4 = c5 = new Carta(-1, 0);
        switch (i) {
            case 4:
                cargarPlayer2(juego[2], juego[3]);
            case 3:
                c5 = juego[8];
            case 2:
                c4 = juego[7];
            case 1:
                c1 = juego[4];
                c2 = juego[5];
                c3 = juego[6];
                cargarMesa(c1, c2, c3, c4, c5);
            case 0:
                if(jToggleButton1.isSelected())
                    cargarPlayer2(juego[2], juego[3]);
                cargarPlayer1(juego[0], juego[1]);
        }
    }

    void iniciarMesa() {
        cargarMesa(new Carta(-1, 0), new Carta(-1, 0), new Carta(-1, 0), new Carta(-1, 0), new Carta(-1, 0));
        cargarPlayer1(new Carta(-1, 0), new Carta(-1, 0));
        cargarPlayer2(new Carta(-1, 0), new Carta(-1, 0));
    }

    public Game() {
        try {
            deck = ImageIO.read(new File("src/img/poker.cards.bypx.png"));
            back = ImageIO.read(new File("src/img/playing-card-back-214x300.jpg"));
        } catch (IOException e) {
        }
        initComponents();
        nuevoJuego(new Humano(this,1000,0), new AI(this,1000,1));
        playerStatus1.parent(this);
        playerStatus2.parent(this);

        log("Interfaz cargada.");
    }

    public int getGameControl() {
        return gameControl;
    }

    public PlayerStatus getPlayerStatus1() {
        return playerStatus1;
    }

    public PlayerStatus getPlayerStatus2() {
        return playerStatus2;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
//    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table1 = new pokerbot.Table();
        playerGraphics1 = new pokerbot.PlayerGraphics();
        playerGraphics2 = new pokerbot.PlayerGraphics();
        playerStatus1 = new pokerbot.PlayerStatus();
        playerStatus2 = new pokerbot.PlayerStatus();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtPozo = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jugar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Texas Hold'em!!");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(table1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));
        getContentPane().add(playerGraphics1, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 0, 322, -1));
        getContentPane().add(playerGraphics2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 322, -1));
        getContentPane().add(playerStatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, -1, -1));
        getContentPane().add(playerStatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 30, 280, 630));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Log");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 4, 230, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Pozo:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jtPozo.setEnabled(false);
        getContentPane().add(jtPozo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 100, -1));

        jToggleButton1.setText("Mostrar Ambas Manos");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, 20));

        jButton1.setText("C");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, -1, 30));

        jugar.setText("Jugar");
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });
        jugar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jugarKeyReleased(evt);
            }
        });
        getContentPane().add(jugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jMenu1.setText("Juego");

        jMenuItem1.setText("Nuevo Juego");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        int accion;
        Object[] Opciones = {"Jugador vs AI", "AI vs AI"};
        accion = JOptionPane.showOptionDialog(null, "Elija el modo de juego", "Juego Nuevo", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opciones, null);
        if (accion == 0) {
            nuevoJuego(new Humano(this, 1000, 0), new AI(this, 1000, 1));
        } else {
            nuevoJuego(new AI(this, 1000, 0), new AI(this, 1000, 1));
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    public boolean mostrarProbabilidad() {
        return jToggleButton1.isSelected();
    }
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if (jToggleButton1.isSelected()) {
            cargarPlayer2(juego[2], juego[3]);
        } else {
            cargarPlayer2(new Carta(-1, 0), new Carta(-1, 0));
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formKeyReleased

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        // TODO add your handling code here:
        Jugador aux;
        log("KeyPressed");
        if(actual!=null){
                aux = actual;
                actual = null;
                aux.siguienteAccion();
        }
    }//GEN-LAST:event_jugarActionPerformed

    private void jugarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jugarKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jugarActionPerformed(null);
        }
    }//GEN-LAST:event_jugarKeyReleased
    public String getVisibleMesaString() {
        String cards = "";
        switch (gameControl) {
            case 3:
                cards += juego[8].toCard().toString() + " ";
            case 2:
                cards += juego[7].toCard().toString() + " ";
            case 1:
                cards += juego[4].toCard().toString() + " ";
                cards += juego[5].toCard().toString() + " ";
                cards += juego[6].toCard().toString() + " ";
        }
        return cards;
    }

    /**
     * @param args the command line arguments
     */
    public void cargarMesa(Carta c1, Carta c2, Carta c3, Carta c4, Carta c5) {
        table1.getTable1().setIcon(ObtenerImagen(c1));
        table1.getTable2().setIcon(ObtenerImagen(c2));
        table1.getTable3().setIcon(ObtenerImagen(c3));
        table1.getTable4().setIcon(ObtenerImagen(c4));
        table1.getTable5().setIcon(ObtenerImagen(c5));

    }

    public void cargarPlayer1(Carta c1, Carta c2) {
        playerGraphics2.getCardGraphic1().setIcon(ObtenerImagen(c1));
        playerGraphics2.getCardGraphic2().setIcon(ObtenerImagen(c2));
    }

    public void cargarPlayer2(Carta c1, Carta c2) {
        playerGraphics1.getCardGraphic1().setIcon(ObtenerImagen(c1));
        playerGraphics1.getCardGraphic2().setIcon(ObtenerImagen(c2));
    }

    public static void main(String args[]) throws ParseException {

        /* Set the Nimbus look and feel */

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                System.out.println(info.getName());
//                if ("Windows Classic".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    //break;
//                }
//            }
            javax.swing.UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("lol");
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>
        Game _this = new Game();
        _this.setVisible(true);

    }

    ImageIcon ObtenerImagen(Carta c) {
        if (c.mazo == -1) {
            return new ImageIcon(resize(back, 160, 220));
        }
        return new ImageIcon(resize(getCard(deck, c), 160, 220));
    }

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return bi;
    }

    public static BufferedImage getCard(BufferedImage bi, Carta c) {
        int x, y;
        x = c.mazo;
        y = c.valor;
        if (x == -1) {
            x = 0;
            y = 0;
        }
        System.out.println(x + " " + y);
        return bi.getSubimage(225 * y, 315 * x, 225, 315);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField jtPozo;
    private javax.swing.JButton jugar;
    private pokerbot.PlayerGraphics playerGraphics1;
    private pokerbot.PlayerGraphics playerGraphics2;
    private pokerbot.PlayerStatus playerStatus1;
    private pokerbot.PlayerStatus playerStatus2;
    private pokerbot.Table table1;
    // End of variables declaration//GEN-END:variables
}
