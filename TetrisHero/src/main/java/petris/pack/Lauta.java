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
    private int curX = 0;
    private int curY = 0;
    private JLabel ylaReuna;
    private Pala palaPelissa;
    private Pentomino[] lauta;

    public Lauta(Petris parent) {
        setFocusable(true);
        palaPelissa = new Pala();
        kello = new Timer(400, this);
        kello.start();
        ylaReuna = parent.getYlaReuna();
        lauta = new Pentomino[LAUTA_LEVEYS * LAUTA_KORKEUS];
        tyhjennaLauta(); // konstruktori tekee tyhjän laudan.
        this.pudotetutPalat = 0; // voi aiheuttaa tuloksen nollautumisen
    }

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

    public int laudanLeveys() {
        return (int) getSize().getWidth() / LAUTA_LEVEYS;
    }

    public int laudanKorkeus() {
        return (int) getSize().getHeight() / LAUTA_KORKEUS;
    }

    public void tyhjennaLauta() {
        for (int i = 0; i < LAUTA_LEVEYS * LAUTA_KORKEUS; i++) {
            lauta[i] = Pentomino.Muodoton;
        }
    }

    public Pentomino palaKohdassa(int x, int y) {
        return lauta[y * LAUTA_LEVEYS + x];
    }

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
    public void uusiPala() {
        palaPelissa.setRandom();
        curX = LAUTA_LEVEYS / 2 + 1;
        curY = LAUTA_KORKEUS - 1 + palaPelissa.minY();

        if (!yritaLiikkua(palaPelissa, curX, curY - 1)) {
            palaPelissa.setPala(Pentomino.Muodoton);
            kello.stop();
            onKaynnissa = false;
            ylaReuna.setText("Peli loppui!");

        }

    }

    public void yksiAskelAlaspain() {
        palanPudotus();
    }

    public Pala palaPelissaNyt() {
        return palaPelissa;
    }

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

    public void piirraLauta(Graphics g) {
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

}
