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
    /* EI TOIMI VIELÄ, KOODI KESKEN
    @Test // asetetaan valmiiksi luokassa, nyt 15
    public void laudanLeveysTest() {
        Lauta lauta = new Lauta(petris);
        int testi = 15;
        assertEquals(testi,lauta.laudanLeveys());

    }
    @Test // asetetaan valmiiksi luokassa, nyt 30
    public void laudanKorkeusTest() {
        Lauta lauta = new Lauta(petris);
        int testi = 30;
        assertEquals(testi, lauta.laudanKorkeus());

    }*/
}
