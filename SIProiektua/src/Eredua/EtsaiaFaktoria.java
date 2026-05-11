package Eredua;

public class EtsaiaFaktoria {
	//singleton
	private static EtsaiaFaktoria nireFaktoria = null;
	
	private static int idKontagailua = 0;

    private EtsaiaFaktoria() {}

    public static EtsaiaFaktoria getNireFaktoria() {
        if (nireFaktoria == null) {
            nireFaktoria = new EtsaiaFaktoria();
        }
        return nireFaktoria;
    }

    public Entitatea sortuEtsaia(int x, int y, String mota) {
    	EtsaiNodo nodo = new EtsaiNodo(x, y);
		
        int unekoId = ++idKontagailua; 

		if (mota.equalsIgnoreCase("MULTIPIXEL")) {
			nodo.gehituOsagaia(new Etsaia(x + 0, y + 0, unekoId));  
			nodo.gehituOsagaia(new Etsaia(x - 1, y + 0, unekoId));  
			nodo.gehituOsagaia(new Etsaia(x + 1, y + 0, unekoId));  
			nodo.gehituOsagaia(new Etsaia(x + 0, y - 1, unekoId));  
			nodo.gehituOsagaia(new Etsaia(x + 0, y + 1, unekoId));  
		}
		return nodo;
    }
}