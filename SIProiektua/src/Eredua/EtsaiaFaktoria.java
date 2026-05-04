package Eredua;

public class EtsaiaFaktoria {
	//singleton
	private static EtsaiaFaktoria nireFaktoria = null;
    private int idKont = 0;

    // 2. ERAIKITZAILEA: Pribatua
    private EtsaiaFaktoria() { }

    // 3. METODOA: Estatikoa izan behar da eta mota berekoa
    public static EtsaiaFaktoria getNireFaktoria() {
        if (nireFaktoria == null) {
            nireFaktoria = new EtsaiaFaktoria();
        }
        return nireFaktoria; // Hemen jada ez dizu errorerik emango
    }

    public Entitatea sortuEtsaia(int x, int y, String mota) {
    	EtsaiNodo nodo = new EtsaiNodo(x, y);
        int id = (int)(Math.random() * 10000);
        nodo.setId(id);

        nodo.gehituOsagaia(new Etsaia(-1, 0, id)); // Ezkerreko "besoa"
        nodo.gehituOsagaia(new Etsaia(0, 0, id));  // Zentroa
        nodo.gehituOsagaia(new Etsaia(1, 0, id));  // Eskumako "besoa"
        nodo.gehituOsagaia(new Etsaia(0, 1, id));  // Beheko "pata"

        return nodo;
    }
}