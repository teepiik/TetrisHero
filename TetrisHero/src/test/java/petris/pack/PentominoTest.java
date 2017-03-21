/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import petris.pack.Pentomino;
import static petris.pack.Pentomino.Muodoton;
import static petris.pack.Pentomino.Pmino;

/**
 *
 * @author teepiik
 *
 */
public class PentominoTest {

    Pentomino pentomino;
    boolean onko;

    // Anna ensiksi construktoriin tai setteriin pentomino, jonka jälkeen testaa onko sama 
    // antamalla toinen pentomino metodille onkoSamaPentomino
    public PentominoTest() {
        this.pentomino = null;
        this.onko = false;
    }

    public void setPentomino(Pentomino pentomino) {
        this.pentomino = pentomino;
    }

    public boolean onkoSamaPentomino(Pentomino testi) {
        onko = false;

        switch (pentomino) {
            case Muodoton:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Fmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Imino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Lmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Nmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Pmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Tmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Umino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Vmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Wmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Xmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Ymino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;

            case Zmino:
                if (this.pentomino == testi) {
                    onko = true;
                }
                break;
        }
        return onko;

    }

    @Test
    public void onkoMuodoton() {
        Pentomino p = Muodoton;
        setPentomino(p);
        Boolean tulos = onkoSamaPentomino(p);
        assertEquals(tulos, true);
    }

    @Test
    public void onkoMuodoton2() {
        Pentomino p = Muodoton;
        Pentomino p2 = Pmino;
        setPentomino(p);
        Boolean tulos = onkoSamaPentomino(p2);
        assertEquals(tulos, false);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
