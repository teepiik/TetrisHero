/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 * Lauta-luokka sisältää pelin graafisen toteutuksen ja pelilogiikan.
 *
 * @author teepiik
 */
public class Lauta extends JPanel implements ActionListener {

    /**
     * Pelilaudan leveys.
     */
    public static final int LAUTA_LEVEYS = 15;

    /**
     * Pelilaudan korkeus.
     */
    public static final int LAUTA_KORKEUS = 30;
    private Timer kello;

    /**
     * Tyhjennettyjen rivien määrä, =score.
     */
    public int tyhjennetytRivit = 0;
    private boolean onKaynnissa = false;
    private boolean onPause = false;
    private boolean palaPudonnutLoppuun = false;

    /**
     * Nykyisen pelissä olevan palan x-koordinaatti.
     */
    public int curX = 0;

    /**
     * Nykyisen pelissä olevan palan y-koordinaatti.
     */
    public int curY = 0;

    /**
     * Palkki alalaidassa, jossa näkyy tulos / status.
     */
    public JLabel tulosTaulu;

    /**
     * Pelattavissa oleva Pala-olio laudalla.
     */
    public Pala palaPelissa;
    private Pentomino[] lauta;

    /**
     * Lauta-luokan konstruktori, joka myös käynnistää pelin.
     *
     * @param parent tyyppi
     */
    public Lauta(Petris parent) {
        setFocusable(true);
        palaPelissa = new Pala();
        kello = new Timer(300, this);
        kello.start();
        tulosTaulu = parent.getTulosTaulu();
        lauta = new Pentomino[LAUTA_LEVEYS * LAUTA_KORKEUS];
        tyhjennaLauta(); // konstruktori tekee tyhjän laudan.
        addKeyListener(new PetrisKeyAdapter(this));

    }

    /**
     * Parametriton konstruktori testejä varten.
     */
    public Lauta() {
        // Tämä on vain testiä varten.

    }

    /**
     * Käsittelee pelin saaman keyboard syötteen, sisältää myös hieman pelin
     * logiikkaa.
     *
     * @param ae actionevent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // jos pala pudonnut loppuun eli true, tehdään uusi pala, muuten pudotetaan lisää.
        if (palaPudonnutLoppuun) {
            palaPudonnutLoppuun = false; // alustetaan uutta palaa varten
            uusiPala();
        } else {
            yksiRiviAlaspain();
        }

    }

    /**
     * Palauttaa Petrispelin laudan leveyden Integerinä.
     *
     * @return integer
     */
    public int laudanLeveys() {
        return (int) getSize().getWidth() / LAUTA_LEVEYS;
    }

    /**
     * Palauttaa Petrispelin laudan korkeuden Integerinä.
     *
     * @return Integer
     */
    public int laudanKorkeus() {
        return (int) getSize().getHeight() / LAUTA_KORKEUS;
    }

    /**
     * Tyhjentää Petrispelin laudan asettamalla kaikkien koordinaattien
     * Pentominoksi muodottoman eli tyhjän.
     */
    public void tyhjennaLauta() {
        for (int i = 0; i < LAUTA_LEVEYS * LAUTA_KORKEUS; i++) {
            lauta[i] = Pentomino.Muodoton;
        }
    }

    /**
     * Palauttaa Pentominon kohdassa (x,y), "Muodoton" tarkoitaa käytännössä
     * tyhjää.
     *
     * @param x koordinaatti
     * @param y koordinaatti
     * @return Pentomino
     */
    public Pentomino palaKohdassa(int x, int y) {
        return lauta[y * LAUTA_LEVEYS + x];
    }

    /**
     * Pudottaa palaa alaspäin sekä katsoo, onko pala pudonnut loppuun.
     */
    public void palanPudotus() {
        for (int i = 0; i < 5; i++) {
            int x = curX + palaPelissa.getX(i);
            int y = curY - palaPelissa.getY(i);
            lauta[y * LAUTA_LEVEYS + x] = palaPelissa.getMuoto();
        }
        tyhjennaTaydetRivit();

        if (!palaPudonnutLoppuun) {
            uusiPala();
        }
    }

    // palan liikuttamista varten myös
    /**
     * Luo uuden Palan peliin, sisältää myös samalla pelin logiikkaa.
     */
    public void uusiPala() {
        palaPelissa.setRandom();
        curX = LAUTA_LEVEYS / 2 + 1;
        curY = LAUTA_KORKEUS - 1 + palaPelissa.minY();

        // ei enää tilaa uudelle palalle
        if (!yritaLiikkua(palaPelissa, curX, curY - 1)) {
            palaPelissa.setPala(Pentomino.Muodoton);
            kello.stop();
            onKaynnissa = false;
            tulosTaulu.setText("Peli loppui!");

        }

    }

    /**
     * Kutsuu toista metodia, käytännössä liikuttaa palaa askeleen alaspäin.
     */
    public void yksiRiviAlaspain() {
        if (!yritaLiikkua(palaPelissa, curX, curY - 1)) {
            palanPudotus();
        }
    }

    /**
     * Palauttaa sen palan, joka tällä hetkellä on pelissä. ( = on laudan
     * attribuuttina "palaPelissa".)
     *
     * @return Pala-olio
     */
    public Pala palaPelissaNyt() {
        return palaPelissa;
    }

    /**
     * Metodi palaPelissa attribuutissa olevan Pala-olion muodon muuttamiseksi
     * laudan kautta.
     *
     * @param pento Pentomino
     */
    public void asetaPelissaOlevanPalanMuoto(Pentomino pento) {
        palaPelissa.setMuoto(pento);
    }

    /**
     * Piirtää lautaa ja Paloja laudalle.
     *
     * @param g Grapics
     * @param x koordinaatti
     * @param y koordinaatti
     * @param muoto Pentomino
     */
    public void piirra(Graphics g, int x, int y, Pentomino muoto) {
        Color vari = muoto.color;
        g.setColor(vari);
        g.fillRect(x + 1, y + 1, laudanLeveys() - 2, laudanKorkeus() - 2);
        g.setColor(vari.brighter());
        g.setColor(vari.darker());

    }

    /**
     * Piirtää laudan kokonaisuutena.
     *
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension koko = getSize();

        int laudanKatto = (int) koko.getHeight() - LAUTA_KORKEUS * laudanKorkeus();

        for (int i = 0; i < LAUTA_KORKEUS; i++) {
            for (int j = 0; j < LAUTA_LEVEYS; j++) {
                Pentomino pentomino = palaKohdassa(j, LAUTA_KORKEUS - i - 1);

                if (pentomino != Pentomino.Muodoton) {
                    piirra(g, j * laudanLeveys(), laudanKatto + i * laudanKorkeus(), pentomino);
                }
            }
        }
        if (palaPelissa.getMuoto() != Pentomino.Muodoton) {
            for (int i = 0; i < 5; i++) {
                int x = curX + palaPelissa.getX(i);
                int y = curY - palaPelissa.getY(i);
                piirra(g, x * laudanLeveys(), laudanKatto + (LAUTA_KORKEUS - y - 1) * laudanKorkeus(), palaPelissa.getMuoto());
            }
        }
    }

    private boolean yritaLiikkua(Pala uusiPala, int uusiX, int uusiY) {
        for (int i = 0; i < 5; i++) {
            int x = uusiX + uusiPala.getX(i);
            int y = uusiY - uusiPala.getY(i);

            // Liikkuminen yli laudan
            if (x < 0 || x >= LAUTA_LEVEYS || y < 0 || y >= LAUTA_KORKEUS) {
                return false;
            }
            // yrittää siirtyä toisen palan päälle
            if (palaKohdassa(x, y) != Pentomino.Muodoton) {
                return false;
            }

        }
        palaPelissa = uusiPala;
        curX = uusiX;
        curY = uusiY;
        repaint();

        return true;
    }

    /**
     * Tekee uuden palan, jonka muodoksi annetaan parametrina annettu muoto.
     *
     * @param pentomino Pentomino
     * @return Pala-olio
     */
    public Pala asetaPalaPentominolla(Pentomino pentomino) {
        Pala pala = new Pala();
        pala.setMuoto(pentomino);
        return pala;
    }

    /**
     * Tyhjentää laudalta täydet rivit, sekä lisää ne scoreen.
     */
    public void tyhjennaTaydetRivit() {
        int taysiaRiveja = 0;

        for (int i = LAUTA_KORKEUS - 1; i >= 0; i--) {
            boolean riviOnTaysi = true;

            for (int j = 0; j < LAUTA_LEVEYS; j++) {
                if (palaKohdassa(j, i) == Pentomino.Muodoton) {
                    riviOnTaysi = false;
                    break;
                }
            }
            if (riviOnTaysi) {
                taysiaRiveja++;

                for (int a = i; a < LAUTA_KORKEUS - 1; a++) {
                    for (int b = 0; b < LAUTA_LEVEYS; b++) {
                        lauta[a * LAUTA_LEVEYS + b] = palaKohdassa(b, a + 1);
                    }
                }
            }
            if (taysiaRiveja > 0) {
                tyhjennetytRivit += taysiaRiveja;
                tulosTaulu.setText(String.valueOf(tyhjennetytRivit));
                palaPudonnutLoppuun = true;
                palaPelissa.setPala(Pentomino.Muodoton);
                repaint();
            }
        }

    }

    /**
     * Käynnistää pelin, laittaa takaisin päälle.
     */
    public void kaynnistys() {
        if (onPause) {
            return;
        }
        onKaynnissa = true;
        palaPudonnutLoppuun = false;
        tyhjennetytRivit = 0;
        tyhjennaLauta();
        uusiPala();
        kello.start();
    }

    /**
     * Palauttaa booleanin onko peli käynnissä
     *
     * @return Boolean
     */
    public boolean onkoKaynnissa() {
        return onKaynnissa;
    }

    /**
     * Laittaa pelin pause-tilaan.
     */
    public void pysaytys() {
        if (!onKaynnissa) {
            return;
        }
        onPause = true;
        if (onPause) {
            kello.stop();
            tulosTaulu.setText("Pysäytetty");
        } else {
            kello.start();
            tulosTaulu.setText(String.valueOf(tyhjennetytRivit));
        }
        repaint();
    }

    /**
     * Pudottaa pelissä olevan palan loppuun asti.
     */
    public void pudotaKokonaanAlas() {
        int uusY = curY;

        while (uusY > 0) {
            if (!yritaLiikkua(palaPelissa, curX, uusY - 1)) {
                break;
            }
            uusY--;
        }
        palanPudotus();
    }

    boolean onkoPause() {
        return onPause;
    }

    /**
     * Palauttaa score-rivit.
     *
     * @return Integer
     */
    public int getDropatutRivit() {
        return this.tyhjennetytRivit;

    }

    /**
     * Palauttaa pelissä olevan palan muodon.
     *
     * @return Pentomino
     */
    public Pentomino getPeliPalanMuoto() {
        return palaPelissa.getMuoto();
    }

    /**
     * Asettaa pelin pauselle.
     */
    public void laitaPauselle() {
        this.onPause = true;
    }

    /**
     * Liikuttaa pelissä olevaa palaa askeleen vasemmalle.
     */
    public void keyVasemmalle() {
        yritaLiikkua(palaPelissa, curX - 1, curY);
    }

    /**
     * Liikuttaa pelissä olevaa palaa askeleen oikealle.
     */
    public void keyOikealle() {
        yritaLiikkua(palaPelissa, curX + 1, curY);
    }

    /**
     * Kääntää palaa ympäri.
     */
    public void keyAlasKaanto() {
        yritaLiikkua(palaPelissa.kaannaOikea(), curX, curY);
    }

    /**
     * Kääntää palaa ympäri.
     */
    public void keyYlosKaanto() {
        yritaLiikkua(palaPelissa.kaannaVasen(), curX, curY);
    }

}
