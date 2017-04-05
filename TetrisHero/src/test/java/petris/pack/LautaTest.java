/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author teemu
 */
public class LautaTest {

    Petris petris = new Petris();

    public LautaTest() {
    }

    @Test
    public void laudanTekoTekeePalanTest() {
        Lauta lauta = new Lauta(petris);
        Pala pala = lauta.palaPelissaNyt();
        boolean onko = Objects.nonNull(pala);
        assertEquals(onko, true);

    }

    @Test // huom. ei toimi random palalla, koska assertEqual vaihtelee, osa koord[0] on miinuksella alussa osa ei
    public void liikuAlaspainTest() {
        Lauta lauta = new Lauta(petris);
        lauta.asetaPelissaOlevanPalanMuoto(Pentomino.Fmino);
        Pala pala = lauta.palaPelissaNyt();
        int i = pala.getY(0);
        lauta.yksiAskelAlaspain();
        int j = pala.getY(0);
        assertEquals(i, j - 1);
        // koska alotuksessa -1

    }
     
    @Test
    public void palaKohdassaTest() {
        Lauta lauta = new Lauta(petris);
        Pala pala = lauta.palaPelissaNyt();
        int x = pala.getX(0);
        int y = pala.getY(0);
        Pentomino testi = lauta.palaKohdassa(x, y);
        Pala testi1 = lauta.asetaPalaPentominolla(testi);
        assertEquals(pala.getMuoto(), testi1.getMuoto());

    } 
    
    /* Ei toimi vielä
    @Test
    public void tyhjennaLautaTest() {
        Lauta lauta = new Lauta(petris);
        Pala pala = lauta.palaPelissaNyt();
        int x = pala.getX(0);
        int y = pala.getY(0);
        boolean eiMuodoton = false;
        if (pala.getMuoto()!=Pentomino.Muodoton) {
            eiMuodoton = true;
        }
        lauta.tyhjennaLauta();
        Pentomino p = Pentomino.Muodoton;
        Pentomino q = lauta.palaKohdassa(x, y);
        assertEquals(eiMuodoton, true);
        //assertEquals(p, q);

    } */

}
