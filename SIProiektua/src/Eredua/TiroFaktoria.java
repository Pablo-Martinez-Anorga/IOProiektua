package Eredua;

public class TiroFaktoria {
	
	private static TiroFaktoria nireFaktoria = null;

	private TiroFaktoria() {}

	public static TiroFaktoria getNireFaktoria() {
		if (nireFaktoria == null) {
			nireFaktoria = new TiroFaktoria();
		}
		return nireFaktoria;
	}

	public Entitatea sortuTiroa(int x, int y, String mota) {
		
		if (mota.equals("GEZI")) {
			// GEZI: Multipixela
			TiroNodo nodo = new TiroNodo(x, y);
			nodo.gehituOsagaia(new Tiroa(0, 0));  // Punta
			nodo.gehituOsagaia(new Tiroa(-1, 1)); // Ala ezkerraldea
			nodo.gehituOsagaia(new Tiroa(1, 1));  // Ala eskuinaldea
			return nodo;
			
		} else if (mota.equals("ERRONBO")) {
			// ERRONBO: Multipixela
			TiroNodo nodo = new TiroNodo(x, y);
			nodo.gehituOsagaia(new Tiroa(0, -1)); // Goiko punta
			nodo.gehituOsagaia(new Tiroa(-1, 0)); // Ezkerreko punta
			nodo.gehituOsagaia(new Tiroa(1, 0));  // Eskuineko punta
			nodo.gehituOsagaia(new Tiroa(0, 1));  // Beheko punta
			nodo.gehituOsagaia(new Tiroa(0, 0));  // Erdigunea
			return nodo;
			
		} else {
			// PIXEL
			return new Tiroa(x, y);
		}
	}
}
