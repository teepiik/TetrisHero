/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teepiik
 */
public class PalaTest {

    /**
     *
     */
    public PalaTest() {
    }

    /**
     *
     */
    @Test
    public void tyhjaPalaTest() {
        Pala pala = new Pala();
        int y = 0;
        int x = 0;
        assertEquals(x, pala.getX(x));
        assertEquals(y, pala.getY(x));
        assertEquals(Pentomino.Muodoton, pala.getMuoto());
    }

    /**
     *
     */
    @Test // testa samalla myös muodon getterin
    public void muodonAsetusTest() {
        Pala pala = new Pala();
        pala.setPala(Pentomino.Nmino);
        assertEquals(Pentomino.Nmino, pala.getMuoto());
    }

    /**
     *
     */
    @Test // testa samalla myös palan gettereitä
    public void muodonAsetusKoordinaatitTest() {
        Pala pala = new Pala();
        pala.setPala(Pentomino.Fmino);
        assertEquals(pala.getX(0), -1);
        assertEquals(pala.getX(1), 0);
        assertEquals(pala.getY(3), 1);
        assertEquals(pala.getY(2), -1);
    }

    /**
     *
     */
    @Test //huom. kaannaOikealle palauttaa returnina käännetyn palan, pelkkä metodin suoritus ei riitä
    public void kaannaOikealleTest() {
        Pala pala = new Pala();
        pala.setPala(Pentomino.Fmino);
        pala = pala.kaannaOikea();
        assertEquals(pala.getX(0), 0);
        assertEquals(pala.getX(1), 0);
        assertEquals(pala.getX(2), 1);
        assertEquals(pala.getX(3), -1);
        assertEquals(pala.getX(4), -1);
        assertEquals(pala.getY(0), -1);
        assertEquals(pala.getY(1), 0);
        assertEquals(pala.getY(2), 0);
        assertEquals(pala.getY(3), 0);
        assertEquals(pala.getY(4), 1);

    }

    /**
     *
     */
    @Test //huom. kaannaVasemmalle palauttaa returnina käännetyn palan, pelkkä metodin suoritus ei riitä
    public void kaannaVasemmalleTest() {
        Pala pala = new Pala();
        pala.setPala(Pentomino.Fmino);
        pala = pala.kaannaVasen();
        assertEquals(pala.getX(0), 0);
        assertEquals(pala.getX(1), 0);
        assertEquals(pala.getX(2), -1);
        assertEquals(pala.getX(3), 1);
        assertEquals(pala.getX(4), 1);
        assertEquals(pala.getY(0), 1);
        assertEquals(pala.getY(1), 0);
        assertEquals(pala.getY(2), 0);
        assertEquals(pala.getY(3), 0);
        assertEquals(pala.getY(4), -1);

    }

    /**
     *
     */
    @Test
    public void setRandomTest() {
        Pala pala = new Pala();
        pala.setRandom();
        boolean eiMuodoton = false;
        boolean eiTyhja = false;
        if (pala.getMuoto() != Pentomino.Muodoton) {
            eiMuodoton = true;
        }
        if (pala.getMuoto() != null) {
            eiTyhja = true;
        }
        assertEquals(eiMuodoton, true);
        assertEquals(eiTyhja, true);
    }

    /**
     *
     */
    @Test
    public void minXTest() {
        Pala pala = new Pala();
        pala.setPala(Pentomino.Muodoton);
        assertEquals(pala.minX(), 0);
        pala.setPala(Pentomino.Fmino);
        assertEquals(pala.minX(), -1);
    }

    /**
     *
     */
    @Test
    public void minYTest() {
        Pala pala = new Pala();
        pala.setPala(Pentomino.Nmino);
        assertEquals(pala.minY(), -1);
        pala.setPala(Pentomino.Imino);
        assertEquals(pala.minY(), -2);
    }

}
