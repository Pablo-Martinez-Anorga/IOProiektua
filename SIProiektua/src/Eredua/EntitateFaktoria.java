package Eredua;

public class EntitateFaktoria {

    private static EntitateFaktoria nireFaktoria = null;

    private EntitateFaktoria() {
    }

    public static EntitateFaktoria getNireFaktoria() {
        if (nireFaktoria == null) {
            nireFaktoria = new EntitateFaktoria();
        }
        return nireFaktoria;
    }

    // Espaziontzia sortzeko metodoa (kolorearen arabera)
    public Espaziontzia sortuEspaziontzia(String mota, int x, int y) {
        Espaziontzia ontzia = new Espaziontzia(x, y, mota);
        // Oharra: Aurrerago hemen konfiguratuko dugu ontzi bakoitzaren estrategia
        // Adibidez: if (mota.equals("GREEN")) ontzia.setEstrategia(new TiroPixelEstrategia());
        return ontzia;
    }

    // Etsaia sortzeko metodoa
    public Etsaia sortuEtsaia(int x, int y) {
        return new Etsaia(x, y);
    }
    
    // Tiroa sortzeko metodoa (aukerakoa, baina garbia)
    public Tiroa sortuTiroa(int x, int y) {
        return new Tiroa(x, y);
    }
}
