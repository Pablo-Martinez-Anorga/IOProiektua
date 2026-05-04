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
	        tiroNodoa.gehituOsagaia(new Tiroa(x + 0, y + 0)); 
	    } else if (mota.equalsIgnoreCase("ERRONBO")) {
	        tiroNodoa.gehituOsagaia(new Tiroa(x + 0, y + 0));   
	        tiroNodoa.gehituOsagaia(new Tiroa(x - 1, y + 1));  
	        tiroNodoa.gehituOsagaia(new Tiroa(x + 1, y + 1));   
	        tiroNodoa.gehituOsagaia(new Tiroa(x + 0, y + 2));   
	    } else if (mota.equalsIgnoreCase("GEZI")) {
	        tiroNodoa.gehituOsagaia(new Tiroa(x + 0, y + 0));   
	        tiroNodoa.gehituOsagaia(new Tiroa(x - 1, y + 1));  
	        tiroNodoa.gehituOsagaia(new Tiroa(x + 1, y + 1));
	        tiroNodoa.gehituOsagaia(new Tiroa(x + 0, y + 1));
	    }
	    return tiroNodoa;
	}
}
