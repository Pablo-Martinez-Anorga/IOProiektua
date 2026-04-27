package Eredua;

public class TiroFaktoria {
	private static TiroFaktoria nireFaktoria = null;

    private TiroFaktoria() {}

    public static TiroFaktoria getNireFaktoria() {
        if (nireFaktoria == null) nireFaktoria = new TiroFaktoria();
        return nireFaktoria;
    }

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
            irudia.gehituOsagaia(new Puntu(0, -2));
            irudia.gehituOsagaia(new Puntu(-1, -1));
            irudia.gehituOsagaia(new Puntu(0, -1));
            irudia.gehituOsagaia(new Puntu(1, -1));
            irudia.gehituOsagaia(new Puntu(-2, 0));
            irudia.gehituOsagaia(new Puntu(-1, 0));
            irudia.gehituOsagaia(new Puntu(0, 0));
            irudia.gehituOsagaia(new Puntu(1, 0));
            irudia.gehituOsagaia(new Puntu(2, 0));
            irudia.gehituOsagaia(new Puntu(-1, 1));
            irudia.gehituOsagaia(new Puntu(0, 1));
            irudia.gehituOsagaia(new Puntu(1, 1));
            irudia.gehituOsagaia(new Puntu(0, 2));
        }
        return tiroa;
    }
}
