/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author teepiik
 */
public class Petris extends JFrame {

    private JLabel ylaReuna;

    public Petris() {
        ylaReuna = new JLabel("0");
        Lauta lauta = new Lauta(this);
        add(lauta);
        setSize(400, 600);
        setTitle("Petris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lauta.uusiPala();
    }

    public JLabel getYlaReuna() {
        return ylaReuna;
    }

}
