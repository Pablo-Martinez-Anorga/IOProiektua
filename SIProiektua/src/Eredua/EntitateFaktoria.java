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
		// Etsaiaren forma (Gurutze baten antzekoa PDFaren arabera)
		irudia.gehituOsagaia(new Puntu(0,0));
		irudia.gehituOsagaia(new Puntu(-1,0));
		irudia.gehituOsagaia(new Puntu(1,0));
		irudia.gehituOsagaia(new Puntu(0,-1));
		return etsaia;
	}
    
    // Tiroa sortzeko metodoa (aukerakoa, baina garbia)
    public Tiroa sortuTiroa(int x, int y) {
        return new Tiroa(x, y);
    }
}
