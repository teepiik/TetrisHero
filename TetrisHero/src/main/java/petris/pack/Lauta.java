/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 *
 * @author teepiik
 */
public class Lauta extends JPanel implements ActionListener{
    
    private static final int LAUTA_LEVEYS = 15;
    private static final int LAUTA_KORKEUS = 30;
    private Timer kello;
    private int pudotetutPalat = 0;
    private boolean onKaynnissa = false;
    private boolean onPause = false;
    private boolean palaPudonnutLoppuun = false;
    private int curX = 0;
    private int curY = 0;
    private JLabel ylaReuna;
    private Pala palaPelissa;
    private Pentomino[] lauta;
    
    public Lauta(Petris parent) {
        setFocusable(true);
        palaPelissa = new Pala();
        kello = new Timer(500, this);
        ylaReuna = parent.getYlaReuna();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
