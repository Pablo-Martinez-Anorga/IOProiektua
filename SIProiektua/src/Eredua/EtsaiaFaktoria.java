package Eredua;

public class EtsaiaFaktoria {
    private static EtsaiaFaktoria nireFaktoria = null;
    private int etsaiKontagailua = 0;

    private EtsaiaFaktoria() {}

    public static EtsaiaFaktoria getNireFaktoria() {
        if (nireFaktoria == null) nireFaktoria = new EtsaiaFaktoria();
        return nireFaktoria;
    }

    public Entitatea sortuEtsaia(int x, int y, String mota) {
    	EtsaiNodo nodoBerria = new EtsaiNodo(x, y); 
        etsaiKontagailua++; 
        
        // 3x3-ko etsaia sortzen du adibidez. 
        // (Zure etsaiaren itxura ezberdina bada, for hau aldatu dezakezu)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Etsaia pixel = new Etsaia(i, j, etsaiKontagailua); 
                nodoBerria.gehituOsagaia(pixel); 
            }
        }
        return nodoBerria;
    }
}

