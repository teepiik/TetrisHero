/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petris.pack;

import java.awt.Color;

/**
 * Tämä Enum luokka sisältää kaikki pelin Pentominojen muodot koordinaatteina
 * sekä niille asetetut värit.
 *
 * @author teepiik
 */
public enum Pentomino {
    Muodoton(new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}}, new Color(0, 0, 0)),
    Fmino(new int[][]{{-1, 0}, {0, 0}, {0, -1}, {0, 1}, {1, 1}}, new Color(204, 204, 102)),
    Imino(new int[][]{{0, -2}, {0, -1}, {0, 0}, {0, 1}, {0, 2}}, new Color(102, 204, 204)),
    Lmino(new int[][]{{0, -1}, {1, -1}, {0, 1}, {0, 2}, {0, 0}}, new Color(102, 102, 204)),
    Nmino(new int[][]{{-1, -1}, {-1, 0}, {0, 0}, {0, 1}, {0, 2}}, new Color(204, 204, 204)),
    Pmino(new int[][]{{0, -1}, {0, 0}, {0, 1}, {1, 0}, {1, 1}}, new Color(102, 102, 102)),
    Tmino(new int[][]{{0, -1}, {0, 0}, {0, 1}, {-1, 1}, {1, 1}}, new Color(204, 102, 204)),
    Umino(new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}}, new Color(102, 102, 204)),
    Vmino(new int[][]{{0, 1}, {0, 0}, {0, -1}, {1, -1}, {2, -1}}, new Color(102, 204, 102)),
    Wmino(new int[][]{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}, {1, -1}}, new Color(102, 204, 204)),
    Xmino(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, 1}, {0, -1}}, new Color(218, 170, 0)),
    Ymino(new int[][]{{-1, 0}, {0, 1}, {0, 0}, {0, -1}, {0, -2}}, new Color(0, 170, 218)),
    Zmino(new int[][]{{-1, 1}, {0, 1}, {0, 0}, {0, -1}, {1, -1}}, new Color(0, 218, 0));

    public int[][] koordinaatit;
    public Color color;

    private Pentomino(int[][] coords, Color c) {
        this.koordinaatit = coords;
        color = c;
    }

}
