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
	protected int geziMunizioa = 30;
	protected int erronboMunizioa = 20;
	
	// Eraikitzailea
	public EspaziontziNodo(int x, int y) { 
		super(x, y);
		itxuraSortu(); // Gorria, Urdina, Berdea 
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
		for (Entitatea p : osagaiak) {
            int px = p.getX();
            int py = p.getY();
            if (norabidea.equals("Ezkerrera") && px <= 0) return false;
            if (norabidea.equals("Eskumara") && px >= 99) return false;
            if (norabidea.equals("Gora") && py <= 0) return false;
            if (norabidea.equals("Behera") && py >= 59) return false;
        }
        return true;
    }
	
	@Override
    public void mugitu(String norabidea) {
		if (mugituDaiteke(norabidea)) {
            //Nodoaren posizioa eguneratu
            if (norabidea.equals("Eskumara")) this.x++;
            else if (norabidea.equals("Ezkerrera")) this.x--;
            else if (norabidea.equals("Gora")) this.y--;
            else if (norabidea.equals("Behera")) this.y++;
            
            for (Entitatea p : osagaiak) {
                p.mugitu(norabidea);
            }
        }
    }

	@Override
	public void mugitu() {
		for (Entitatea pixel : osagaiak) {
			pixel.mugitu();
		}
	}
	
	public List<Entitatea> tiroEgin() {
		if (this.armaAktiboa instanceof TiroErronboEstrategia) {
            if (this.erronboMunizioa <= 0) return new ArrayList<>(); 
            this.erronboMunizioa--;
        } else if (this.armaAktiboa instanceof TiroGeziEstrategia) {
            if (this.geziMunizioa <= 0) return new ArrayList<>(); 
            this.geziMunizioa--;
        }

        if (armaAktiboa != null && zentroa != null) {
            int absX = zentroa.getX();
            int absY = zentroa.getY();
            return armaAktiboa.tiroEgin(absX, absY - 1, 1);
        }
        return new ArrayList<>();
    }
}