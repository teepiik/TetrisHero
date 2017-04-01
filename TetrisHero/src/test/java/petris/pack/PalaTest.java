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
 * @author teemu
 */
public class PalaTest {

    public PalaTest() {
    }

    @Test
    public void tyhjaPalaTest() {
        Pala pala = new Pala();
        int y = 0;
        int x = 0;
        assertEquals(x, pala.getX(x));
        assertEquals(y, pala.getY(x));
        assertEquals(Pentomino.Muodoton, pala.getMuoto());
    }

    @Test // testa samalla myös muodon getterin
    public void muodonAsetusTest() {
        Pala pala = new Pala();
        pala.setPala(Pentomino.Nmino);
        assertEquals(Pentomino.Nmino, pala.getMuoto());
    }

    @Test
    public void setRandomTest() {
        Pala pala = new Pala();
        pala.setRandom(pala);
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

}
