
Aihe: Tetris peli (Petris)

Käyttäjät: Pelaaja (1 pelaaja)

Pelaajan toiminnot: 
Pelin pelaaminen
    

Pelin kuvaus:
Klassinen tetris uudelleen kehiteltynä Petrikseksi, joka perustuu perinteisen tetriksen 4-osaisten tetronomien     sijaan 5-osaisiin pentominoihin. 


Rakennekuvaus:
Projekti sisältää luokat Pentomino, Pala, Lauta, PetrisKeyAdapter, Petris ja Main (sekä testiluokkia). Pentomino on Enum-luokka, joka sisältää kaikki mahdolliset palat ja niiden "muodot". Pala taas sisältää pelattavien palojen rungon ja käsittelyn vaativat metodit, eli tavallaan antaa Pentominoille vartalon.

Lauta luokka on monsteri, joka sisältää sekä pelin logiikan että myös pitkälti graafisen toteutuksen. Luokka on sekavan näköinen, mutta toisaalta sisältää yhtenäisen kokonaisuuden "pelilauta". PetrisKeyAdapter toteuttaa yhdessä Laudan kanssa pelipalojen ohjauksen näppäimistöllä. Main ja Petris ovat lähinnä korkeampia luokkia, joiden kautta peli käynnistetään. 


