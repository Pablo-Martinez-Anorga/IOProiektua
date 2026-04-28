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
    
    public Espaziontzia sortuEspaziontzia(String mota, int x, int y) {
		if (mota.equals("RED")) {
			return new EspaziontziaGorria(x, y);
		}else if (mota.equals("BLUE")) {
			return new EspaziontziaUrdina(x, y);
		}else {
			return new EspaziontziaBerdea(x, y);
		}
	}

}
