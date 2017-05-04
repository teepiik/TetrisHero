/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import java.util.Random;

/**
 * Pala luokka sisältää Pentominojen rungon, koordinaatit ja niiden toiminnot.
 *
 * @author teepiik
 */
public class Pala {

    private Pentomino palanMuoto;
    private int[][] koordinaatit;

    /**
     * Pala-luokan konstruktori.
     */
    public Pala() {
        palanMuoto = Pentomino.Muodoton;
        koordinaatit = new int[5][2];
    }

    /**
     * Muuttaa Palan muodon parametrina annetuksi Pentominoksi.
     *
     * @param pentomino Pentomino
     */
    public void setPala(Pentomino pentomino) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                koordinaatit[i][j] = pentomino.koordinaatit[i][j];
            }
        }

        palanMuoto = pentomino;
    }

    // Setterit ja Getterit
    public int getX(int indeksi) {
        return koordinaatit[indeksi][0];
    }

    public int getY(int indeksi) {
        return koordinaatit[indeksi][1];
    }

    private void setX(int indeksi, int x) {
        koordinaatit[indeksi][0] = x;
    }

    private void setY(int indeksi, int y) {
        koordinaatit[indeksi][1] = y;
    }

    /**
     * Palauttaa palan Pentomino-muodon.
     *
     * @return Pentomino
     */
    public Pentomino getMuoto() {
        return palanMuoto;
    }

    /**
     * Palan muodon asettaminen manuaalisesti.
     *
     * @param pento
     */
    public void setMuoto(Pentomino pento) {
        this.palanMuoto = pento;
    }

    /**
     * Kääntää palaa 90 astetta vasemmalle.
     *
     * @return Pala-olio
     */
    public Pala kaannaVasen() {
        if (palanMuoto == Pentomino.Xmino) {
            return this;
        }

        Pala uusi = new Pala();
        uusi.palanMuoto = palanMuoto;

        for (int i = 0; i < 5; i++) {
            uusi.setX(i, getY(i));
            uusi.setY(i, -getX(i));
        }

        return uusi;
    }

    /**
     * Kääntää palaa 90 astetta oikealle.
     *
     * @return Pala-olio
     */
    public Pala kaannaOikea() {
        // Xminon muoto ulospäin ei muutu vaikka käännettäisiinkin.
        if (palanMuoto == Pentomino.Xmino) {
            return this;
        }

        Pala uusi = new Pala();
        uusi.palanMuoto = palanMuoto;

        for (int i = 0; i < 5; i++) {
            uusi.setX(i, -getY(i));
            uusi.setY(i, getX(i));
        }

        return uusi;
    }

    /**
     * Asettaa satunnaisen muodon palalle.
     */
    public void setRandom() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 12 + 1;
        Pentomino[] arvot = Pentomino.values();
        setPala(arvot[x]);
    }

    /**
     * Palauttaa palan pienimmän x-koordinaatin
     *
     * @return Integer
     */
    public int minX() {
        int m = koordinaatit[0][0];

        for (int i = 0; i < 5; i++) {
            m = Math.min(m, koordinaatit[i][0]);
        }

        return m;
    }

    /**
     * Palauttaa palan pienimmän y-koordinaatin.
     *
     * @return Integer
     */
    public int minY() {
        int m = koordinaatit[0][1];

        for (int i = 0; i < 5; i++) {
            m = Math.min(m, koordinaatit[i][1]);
        }

        return m;
    }

}
