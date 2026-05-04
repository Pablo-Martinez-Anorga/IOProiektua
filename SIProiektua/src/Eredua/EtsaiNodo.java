package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EtsaiNodo extends Entitatea {
    private List<Entitatea> osagaiak = new ArrayList<>();

    public EtsaiNodo(int x, int y) {
        super(x, y);
        this.osagaiak = new ArrayList<>();
    }

    public void gehituOsagaia(Entitatea e) {
        this.osagaiak.add(e);
    }

    @Override
    public Egoera getEgoeraObject() {
        return new GelaxkaEtsai(); // 1. urratsean aldatu genuen izen berria
    }
    
    public List<Entitatea> getPixelek() {
        return osagaiak; // Zuzenean hostoen zerrenda itzultzen du
    }
    
    public boolean mugituDaiteke(String norabidea) {
        for (Entitatea pixel : osagaiak) {
            if (norabidea.equals("Eskumara") && pixel.getX() >= 99) return false;
            if (norabidea.equals("Ezkerrera") && pixel.getX() <= 0) return false;
        }
        return true; 
    }

    // 1. METODOA: Parametrorik gabea (Etsaiaren IA: Zorizko norabidea erabaki)
    @Override
    public void mugitu() {
        int norabidea = (int)(Math.random() * 3); // 0 ezker, 1 eskuin, 2 behera
        String norabideStr = "";
        
        if (norabidea == 0) norabideStr = "Ezkerrera";
        else if (norabidea == 1) norabideStr = "Eskumara";
        else norabideStr = "Behera";

        // Gure metodo berria erabiltzen dugu mugak pasatzen ez dituela ziurtatzeko
        if (mugituDaiteke(norabideStr)) {
            
            int xBerria = this.x;
            int yBerria = this.y;

            if (norabidea == 0) xBerria--;
            else if (norabidea == 1) xBerria++;
            else yBerria++;

            // JokoKudeatzaileari galdetu ea posizioa libre dagoen
            if (Eredua.JokoKudeatzailea.getNireJK().posizioaLibreDa(xBerria, yBerria, this)) {
                // Nodoaren koordenatu nagusiak eguneratu
                this.x = xBerria;
                this.y = yBerria;
                
                // IRAKASLEAREN TEORIA: Begizta erabili pixel guztiak banan-banan mugitzeko
                for (Entitatea pixel : osagaiak) {
                    pixel.mugitu(norabideStr); // Hostoen mugitu(String) deitzen du
                }
            }
        }
    }
    
    // 2. METODOA: Parametroarekin (Norabide zehatz bat ematen zaionean)
    @Override
    public void mugitu(String norabidea) {
        for (Entitatea pixel : osagaiak) {
            pixel.mugitu(norabidea);
        }
    }
}
