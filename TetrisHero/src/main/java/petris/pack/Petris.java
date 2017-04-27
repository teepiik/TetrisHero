/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author teepiik
 */
public class Petris extends JFrame {

    private JLabel tulosTaulu;

    public Petris() {
        tulosTaulu = new JLabel("0");
        add(tulosTaulu, BorderLayout.SOUTH);
        Lauta lauta = new Lauta(this);
        add(lauta);
        lauta.kaynnistys();
        setSize(400, 600);
        setTitle("Petris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getTulosTaulu() {
        return tulosTaulu;
    }

}
