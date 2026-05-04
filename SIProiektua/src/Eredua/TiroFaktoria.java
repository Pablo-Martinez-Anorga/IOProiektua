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
        tiroNodoa.x = x;
        tiroNodoa.y = y;

        // Tiro sinple bat (pixel 1)
        Tiroa pixel = new Tiroa(0, 0);
        tiroNodoa.gehituOsagaia(pixel);

        return tiroNodoa;
		}
	}

