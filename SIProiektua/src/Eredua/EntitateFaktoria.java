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

    public Espaziontzia sortuEspaziontzia(String mota, int x, int y) {
		Espaziontzia ontzia = new Espaziontzia(x, y, mota);
		IrudiKonposatua irudia = ontzia.getIrudia();
		
		// Pixel zentrala eta alboetakoak (Oinarria denentzat)
		irudia.gehituOsagaia(new Puntu(0, 0));
		irudia.gehituOsagaia(new Puntu(-1, 0));
		irudia.gehituOsagaia(new Puntu(1, 0));
		
		if (mota.equals("RED")) { // U forma (4 pixel)
			irudia.gehituOsagaia(new Puntu(-1, -1));
			irudia.gehituOsagaia(new Puntu(1, -1));
		} else if (mota.equals("BLUE")) { // T alderantzizkoa (4 pixel)
			irudia.gehituOsagaia(new Puntu(0, -1));
		} else if (mota.equals("GREEN")) { // Gurutzea (5 pixel)
			irudia.gehituOsagaia(new Puntu(0, -1));
			irudia.gehituOsagaia(new Puntu(0, 1));
		}
		return ontzia;
	}

    public Etsaia sortuEtsaia(int x, int y) {
        Etsaia etsaia = new Etsaia(x, y);
        IrudiKonposatua irudia = etsaia.getIrudia();
        
        irudia.gehituOsagaia(new Puntu(0, 0));
        irudia.gehituOsagaia(new Puntu(-1, -1));
        irudia.gehituOsagaia(new Puntu(1, -1));
        irudia.gehituOsagaia(new Puntu(-2, -2));
        irudia.gehituOsagaia(new Puntu(2, -2));
        
        return etsaia;
    }
    
    // Tiroa sortzeko metodoa
    public Tiroa sortuTiroa(int x, int y, String mota) {
        Tiroa tiroa = new Tiroa(x, y);
        IrudiKonposatua irudia = tiroa.getIrudia();
        
        if (mota.equals("PIXEL")) {
            irudia.gehituOsagaia(new Puntu(0, 0));
        } else if (mota.equals("GEZI")) { 
            irudia.gehituOsagaia(new Puntu(0, -1)); 
            irudia.gehituOsagaia(new Puntu(-1, 0)); 
            irudia.gehituOsagaia(new Puntu(1, 0));  
        } else if (mota.equals("ERRONBO")) {
            // Fila superior
            irudia.gehituOsagaia(new Puntu(0, -2));
            // Segunda fila
            irudia.gehituOsagaia(new Puntu(-1, -1));
            irudia.gehituOsagaia(new Puntu(0, -1));
            irudia.gehituOsagaia(new Puntu(1, -1));
            // Fila central
            irudia.gehituOsagaia(new Puntu(-2, 0));
            irudia.gehituOsagaia(new Puntu(-1, 0));
            irudia.gehituOsagaia(new Puntu(0, 0));
            irudia.gehituOsagaia(new Puntu(1, 0));
            irudia.gehituOsagaia(new Puntu(2, 0));
            // Cuarta fila
            irudia.gehituOsagaia(new Puntu(-1, 1));
            irudia.gehituOsagaia(new Puntu(0, 1));
            irudia.gehituOsagaia(new Puntu(1, 1));
            // Fila inferior
            irudia.gehituOsagaia(new Puntu(0, 2));
        }
        return tiroa;
    }
}
