package Eredua;

public class EtsaiaFaktoria {
	private static EtsaiaFaktoria nireFaktoria = null;

    private EtsaiaFaktoria() {}

    public static EtsaiaFaktoria getNireFaktoria() {
        if (nireFaktoria == null) nireFaktoria = new EtsaiaFaktoria();
        return nireFaktoria;
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
}
