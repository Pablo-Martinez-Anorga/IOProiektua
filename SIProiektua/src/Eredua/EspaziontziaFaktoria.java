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
    	if (kolorea != null) {
            if (kolorea.equalsIgnoreCase("RED")) {
                return new EspaziontziaGorria(x, y);
            } else if (kolorea.equalsIgnoreCase("BLUE")) {
                return new EspaziontziaUrdina(x, y);
            } else if (kolorea.equalsIgnoreCase("GREEN")) {
                return new EspaziontziaBerdea(x, y); 
            }
        }
        return new EspaziontziaBerdea(x, y);
	}

}
