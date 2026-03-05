package Kontrolatzailea;

import Bista.HasieraPanela;
import Eredua.JokoKudeatzailea;

public class Nagusia {
    // Ziurtatu lerro hau EXACTO dagoela: public static void main(String[] args)
    public static void main(String[] args) {
        // Eredua hasieratu (Singleton)
        JokoKudeatzailea.getNireJK();

        // Bista kargatu (Hasiera leihoa)
        HasieraPanela hp = new HasieraPanela();

        // Kontrolatzailea sortu eta hasierako pantailari eman
        BotoiKontroladorea bk = new BotoiKontroladorea(hp);
        hp.setKontrolatzailea(bk);

        // Erakutsi
        hp.setVisible(true);
    }
}