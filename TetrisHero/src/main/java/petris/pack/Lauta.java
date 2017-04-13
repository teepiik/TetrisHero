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
import static javafx.scene.paint.Color.color;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JPanel;

/**
 *
 * @author teepiik
 */
public class Lauta extends JPanel implements ActionListener {

    private static final int LAUTA_LEVEYS = 15;
    private static final int LAUTA_KORKEUS = 30;
    private static final Color[] VARIT = {new Color(0, 0, 0)};
    private Timer kello;
    private int pudotetutPalat = 0;
    private boolean onKaynnissa = false;
    private boolean onPause = false;
    private boolean palaPudonnutLoppuun = false;
    public int curX = 0;
    public int curY = 0;
    public JLabel tulosTaulu;
    public Pala palaPelissa;
    private Pentomino[] lauta;

    /**
     * Lauta-luokan konstruktori, joka myös käynnistää pelin.
     *
     * @param parent
     */
    public Lauta(Petris parent) {
        setFocusable(true);
        palaPelissa = new Pala();
        kello = new Timer(400, this);
        kello.start();
        tulosTaulu = parent.getTulosTaulu();
        lauta = new Pentomino[LAUTA_LEVEYS * LAUTA_KORKEUS];
        tyhjennaLauta(); // konstruktori tekee tyhjän laudan.
        this.pudotetutPalat = 0; // voi aiheuttaa tuloksen nollautumisen
    }

    /**
     * Käsittelee pelin saaman keyboard syötteen, sisältää myös hieman pelin
     * logiikkaa.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // jos pala pudonnut loppuun eli true, tehdään uusi pala, muuten pudotetaan lisää.
        if (palaPudonnutLoppuun) {
            palaPudonnutLoppuun = false; // alustetaan uutta palaa varten
            uusiPala();
        } else {
            yksiAskelAlaspain();
        }

    }

    /**
     * Palauttaa Petrispelin laudan leveyden Integerinä.
     *
     * @return
     */
    public int laudanLeveys() {
        return (int) getSize().getWidth() / LAUTA_LEVEYS;
    }

    /**
     * Palauttaa Petrispelin laudan korkeuden Integerinä.
     *
     * @return
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
     * @param x
     * @param y
     * @return
     */
    public Pentomino palaKohdassa(int x, int y) {
        return lauta[y * LAUTA_LEVEYS + x];
    }

    /**
     *
     */
    public void palanPudotus() {
        for (int i = 0; i < 5; i++) {
            int x = curX + palaPelissa.getX(i);
            int y = curY - palaPelissa.getY(i);
        }
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
    public void yksiAskelAlaspain() {
        palanPudotus();
    }

    /**
     * Palauttaa sen palan, joka tällä hetkellä on pelissä. ( = on laudan
     * attribuuttina "palaPelissa".)
     *
     * @return
     */
    public Pala palaPelissaNyt() {
        return palaPelissa;
    }

    /**
     * Metodi palaPelissa attribuutissa olevan Pala-olion muodon muuttamiseksi
     * laudan kautta.
     *
     * @param pento
     */
    public void asetaPelissaOlevanPalanMuoto(Pentomino pento) {
        palaPelissa.setMuoto(pento);
    }

    /**
     * Piirtää lautaa ja Paloja laudalle.
     *
     * @param g
     * @param x
     * @param y
     * @param muoto
     */
    public void piirra(Graphics g, int x, int y, Pentomino muoto) {
        Color vari = muoto.color;
        g.setColor(vari);
        g.fillRect(x + 1, y + 1, laudanLeveys() - 2, laudanKorkeus() - 2);
        g.setColor(vari.brighter());
        g.drawLine(x, y, x + laudanLeveys() - 1, y);
        g.drawLine(x, y + laudanKorkeus() - 1, x, y);
        g.setColor(vari.darker());
        g.drawLine(x + 1, y + laudanKorkeus() - 1, x + laudanLeveys(), y - laudanKorkeus() - 1);
        g.drawLine(x + laudanLeveys() - 1, y + laudanKorkeus() - 1, x + laudanLeveys() - 1, y + 1);
    }

    /**
     * Piirtää laudan kokonaisuutena.
     *
     * @param g
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
                int y = curY + palaPelissa.getY(i);
                piirra(g, x * laudanLeveys(), laudanKatto + (LAUTA_KORKEUS - y - 1) * laudanKorkeus(), palaPelissa.getMuoto());
            }
        }
    }

    private boolean yritaLiikkua(Pala uusiPala, int uusiX, int uusiY) {
        for (int i = 0; i < 5; i++) {
            int x = uusiX + uusiPala.getX(i);
            int y = uusiY + uusiPala.getY(i);

            // Liikkuminen yli laudan
            if (x < 0 || x >= LAUTA_LEVEYS || y < 0 || y >= LAUTA_KORKEUS) {
                return false;
            }
            // yrittää siirtyä toisen palan päälle
            if (palaKohdassa(x, y) != Pentomino.Muodoton) {
                return false;
            }
            palaPelissa = uusiPala;
            curX = uusiX;
            curY = uusiY;
            repaint();
        }

        return true;
    }

    /**
     * Tekee uuden palan, jonka muodoksi annetaan parametrina annettu muoto.
     *
     * @param pentomino
     * @return
     */
    public Pala asetaPalaPentominolla(Pentomino pentomino) {
        Pala pala = new Pala();
        pala.setMuoto(pentomino);
        return pala;
    }

    public void tyhjennaTaydetRivit() {
        int taysiaRiveja = 0;

        for (int i = LAUTA_KORKEUS - 1; i >= 0; --i) {
            boolean riviOnTaysi = true;

            for (int j = 0; j < LAUTA_LEVEYS;) {
                if (palaKohdassa(j, i) == Pentomino.Muodoton) {
                    riviOnTaysi = false;
                    break;
                }
            }
            if (riviOnTaysi) {
                pudotetutPalat++;

                for (int a = i; a < LAUTA_KORKEUS - 1; a++) {
                    for (int b = 0; b < LAUTA_KORKEUS; b++) {
                        lauta[a * LAUTA_LEVEYS + b] = palaKohdassa(b, a + 1);
                    }
                }
            } // TODO statsien päivitys
        }

    }

    public void kaynnistys() {
        if (onPause) {
            return;
        }
        onKaynnissa = true;
        palaPudonnutLoppuun = false;
        pudotetutPalat = 0;
        tyhjennaLauta();
        uusiPala();
        kello.start();
    }

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
            tulosTaulu.setText(String.valueOf(pudotetutPalat));
        }
        repaint();
    }

}
