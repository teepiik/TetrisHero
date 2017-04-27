/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Näppäimistön kuuntelija Petris-peliin.
 *
 * @author teepiik
 */
public class PetrisKeyAdapter implements KeyListener {

    Lauta lauta;

    /**
     * Kuuntelija tarvitsee konstruktorin parametrina Lauta-olion.
     *
     * @param l
     */
    public PetrisKeyAdapter(Lauta l) {
        this.lauta = l;
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if (!lauta.onkoKaynnissa() || lauta.getPeliPalanMuoto() == Pentomino.Muodoton) {
            return;
        }
        int keyKoodi = ke.getKeyCode();

        if (keyKoodi == 'p' || keyKoodi == 'P') {
            lauta.laitaPauselle();
        }
        if (lauta.onkoPause()) {
            return;
        }

        switch (keyKoodi) {
            case KeyEvent.VK_LEFT:
                lauta.keyVasemmalle();
                break;

            case KeyEvent.VK_RIGHT:
                lauta.keyOikealle();
                break;

            case KeyEvent.VK_UP:
                lauta.keyYlosKaanto();
                break;

            case KeyEvent.VK_DOWN:
                lauta.keyAlasKaanto();
                break;

            case KeyEvent.VK_SPACE:
                lauta.pudotaKokonaanAlas();
                break;
        }
    }

    /**
     *
     * @param ke
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
