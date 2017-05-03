Testaus-Dokumentaatio
 
Tämän projektityön JUnit testit ovar varsin puutteelliset, suurelta osin sen takia että koin, etteivät ne todella olleet tarpeellisia. Tetris on pelinä hyvin yksinkertainen, eikä tällä projektilla muuten (nyt ainakaan palautusvaiheessa) ole juurikaan muuta toiminnallisuutta itse pelin lisäksi. Myös palojen Random-muodot aiheuttivat ongelmia testien tekemiseen. Kuitenkin ehdottomasti suurin syy testien puutteeseen oli yksinkertaisesti ajan puute.

Peli kuitenkin toimii niin kuin pitääkin, olen pelannut sitä useampia pelejä, enkä ole törmännyt yhteenkään bugiin. En kylläkään ole pelannut peliä hirveän pitkälle ikinä (Petris osoittautui huomattavasti Tetristä huonommaksi peliksi), mutta en usko pelin etenemisen vaikuttavan bugeihin. Tietenkin ennen tätä palautushetkeä bugeja oli erittäin paljon, mutta niiden testaamisen suoritin myös itse pelaamalla peliä, en niinkään JUnit testeillä, koska näin se oli huomattavasti nopeampaa ja myös käytännönläheisempää. 

Erityisesti KeyAdapter-luokalle en ole tehnyt mitään testejä, näppäimet yksinkertaisesti toimivat niin kuin pitääkin. Tämän testaaminen suoraan peliä pelaamalla oli huomattavasti mielekkäämpää, nopeampaa ja järkevämpää. 

Esimerkkejä korjatuista bugeista: Ennen päivitystä space-näppäimellä pala pysähtyi ilmaan, eikä pudonnutkaan alas. Syyksi osoittautui suurempikuin-merkki väärin päin invariantissa.
Toinen bug oli , että peli kaatui täyden rivin syntyessä vaakatasoon. Tässä virhe oli monimutkaisempi, ja vaati pidemmän selvittelyn.