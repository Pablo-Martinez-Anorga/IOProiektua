package Eredua;

public class EspaziontziaFaktoria {
	//singleton
	private static EspaziontziaFaktoria nireFaktoria = null;

    private EspaziontziaFaktoria() {}

    public static EspaziontziaFaktoria getNireFaktoria() {
        if (nireFaktoria == null) {
        	nireFaktoria = new EspaziontziaFaktoria();
        }
        return nireFaktoria;
    }
    
    public EspaziontziNodo sortuEspaziontzia(String kolorea, int x, int y) {
    	if (kolorea != null && kolorea.equalsIgnoreCase("GORRIA")) {
            return new EspaziontziaGorria(x, y);
        } else if (kolorea != null && kolorea.equalsIgnoreCase("URDINA")) {
            return new EspaziontziaUrdina(x, y);
        }
        
        // Defektuz Gorria itzuli (edo daukazun beste bat)
        return new EspaziontziaGorria(x, y);
	}

}
