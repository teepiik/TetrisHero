/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import javax.swing.JLabel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teepiik
 */
public class PetrisTest {

    public PetrisTest() {
    }

    @Test
    public void GetTulosTauluTest() {
        Petris instance = new Petris();
        JLabel testi = instance.getTulosTaulu();
        boolean toimiiko = true;
        if (testi == null) {
            toimiiko = false;
        }
        assertEquals(toimiiko, true);

    }

}
