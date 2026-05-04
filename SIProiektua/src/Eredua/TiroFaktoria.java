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
		TiroNodo tiroNodoa = new TiroNodo(x, y);
		if (mota.equalsIgnoreCase("PIXEL")) {
	        // Tiro simple: 1 solo píxel
	        tiroNodoa.gehituOsagaia(new Tiroa(0, 0)); 
	        
	    } else if (mota.equalsIgnoreCase("ERRONBO")) {
	        // Forma de Rombo (Composite)
	        tiroNodoa.gehituOsagaia(new Tiroa(0, 0));   // Punta
	        tiroNodoa.gehituOsagaia(new Tiroa(-1, 1));  // Izquierda
	        tiroNodoa.gehituOsagaia(new Tiroa(1, 1));   // Derecha
	        tiroNodoa.gehituOsagaia(new Tiroa(0, 2));   // Cola
	        
	    } else if (mota.equalsIgnoreCase("GEZI")) {
	        // Forma de Flecha
	        tiroNodoa.gehituOsagaia(new Tiroa(0, 0));   // Punta
	        tiroNodoa.gehituOsagaia(new Tiroa(-1, 1));  // Ala izquierda
	        tiroNodoa.gehituOsagaia(new Tiroa(1, 1));
	        tiroNodoa.gehituOsagaia(new Tiroa(0, 1));
	    }

	    return tiroNodoa;
	}
}
