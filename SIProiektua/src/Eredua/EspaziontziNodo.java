package Eredua;

import java.util.ArrayList;
import java.util.List;

public abstract class EspaziontziNodo extends Entitatea {
	
	// COMPOSITE: Entitate bilduma
	protected List<Entitatea> osagaiak = new ArrayList<>();
	protected Entitatea zentroa;
	
	// Strategy 
	protected TiroEstrategia armaAktiboa;
	
	// Munizioa
	protected int geziMunizioa;
	protected int erronboMunizioa;
	
	// Eraikitzailea
	public EspaziontziNodo(int x, int y) { 
		super(x, y);
		
		// Munizioa hasieratu
		this.geziMunizioa = 30;
		this.erronboMunizioa = 20;
		
		itxuraSortu(); // Gorria, Urdina, Berdea osagaiak gehituko dituzte hemen
		
		this.armaAktiboa = new TiroPixelEstrategia();
	}

	// Osagaiak gehitzeko metodoa
	public void gehituOsagaia(Entitatea e) {
		this.osagaiak.add(e);
	}
	
	// Zentroa esleitzeko metodoa Faktoriak erabili dezan
	public void setZentroa(Entitatea z) {
		this.zentroa = z;
	}

	// Metodo abstraktuak
	protected abstract void itxuraSortu();
	public abstract void aldatuArma();
	
	public void setArma(TiroEstrategia armaBerria) {
		this.armaAktiboa = armaBerria;
	}
	
	@Override
	public Egoera getEgoeraObject() {
		return new GelaxkaEspaziontzi(); // HutsaEgoera-ren ordez, ontziarena itzuli
	}
	
	public List<Entitatea> getPixelek() {
	    return osagaiak; // Zuzenean hostoen zerrenda itzultzen du
	}
	
	public boolean mugituDaiteke(String norabidea) {
        for (Entitatea pixel : osagaiak) {
            if (norabidea.equals("Eskumara") && pixel.getX() >= 99) return false;
            if (norabidea.equals("Ezkerrera") && pixel.getX() <= 0) return false;
            // Ontzia gora eta behera ere mugitu badaiteke:
            if (norabidea.equals("Gora") && pixel.getY() <= 0) return false;
            if (norabidea.equals("Behera") && pixel.getY() >= 59) return false;
        }
        return true;
    }
	
	@Override
    public void mugitu(String norabidea) {
        if (mugituDaiteke(norabidea)) { // Ontziak bere burua konprobatzen du mugitu aurretik
            // Nodoaren koordenatu nagusiak eguneratu
            if (norabidea.equals("Eskumara")) this.x++;
            else if (norabidea.equals("Ezkerrera")) this.x--;
            else if (norabidea.equals("Gora")) this.y--;
            else if (norabidea.equals("Behera")) this.y++;
        	
            // Pixel bakoitza (hostoa) banan-banan mugitu
            for (Entitatea pixel : osagaiak) {
                pixel.mugitu(norabidea);
            }
        }
    }
	
	// Entitatea klaseko derrigorrezko metodoa
	@Override
	public void mugitu() {
		for (Entitatea pixel : osagaiak) {
			pixel.mugitu();
		}
	}
	
	public List<Entitatea> tiroEgin() {
		// 1. Munizioaren logika zaharretik berreskuratuta
		if (this.armaAktiboa instanceof TiroGeziEstrategia) {
			if (this.geziMunizioa <= 0) return new ArrayList<>(); // Ez dago muniziorik
			this.geziMunizioa--;
		} else if (this.armaAktiboa instanceof TiroErronboEstrategia) {
			if (this.erronboMunizioa <= 0) return new ArrayList<>(); // Ez dago muniziorik
			this.erronboMunizioa--;
		}
		
		// 2. Tiroa ZENTROTIK bakarrik sortu
        if (armaAktiboa != null && zentroa != null) {
            // Zentroko pixelaren X eta Y pasatzen dizkiogu estrategiari
        	// Oharra: "3" pasatzen diot zure kodigo zaharrean horrela zegoelako
            return armaAktiboa.tiroEgin(zentroa.getX(), zentroa.getY() - 1, 3); 
        }
        return new ArrayList<>();
    }
}