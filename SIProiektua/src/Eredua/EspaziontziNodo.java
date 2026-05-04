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
		for (Entitatea p : osagaiak) {
            int px = this.x + p.getX(); //Nodoaren posizioa + Pixelaren posizio erlatiboa
            int py = this.y + p.getY();
            
            if (norabidea.equals("Ezkerrera") && px <= 1) return false;
            if (norabidea.equals("Eskumara") && px >= 99) return false;
            if (norabidea.equals("Gora") && py <= 1) return false;
            if (norabidea.equals("Behera") && py >= 59) return false;
        }
        return true;
    }
	
	@Override
    public void mugitu(String norabidea) {
		if (mugituDaiteke(norabidea)) {
            // 1. Nodoaren posizioa eguneratu
            if (norabidea.equals("Eskumara")) this.x++;
            else if (norabidea.equals("Ezkerrera")) this.x--;
            else if (norabidea.equals("Gora")) this.y--;
            else if (norabidea.equals("Behera")) this.y++;
            
            // 2. Hostoak (pixelak) mugitu
            /*
            for (Entitatea p : osagaiak) {
                p.mugitu(norabidea);
            }
     		*/
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
		if (this.armaAktiboa instanceof TiroErronboEstrategia) {
            if (this.erronboMunizioa <= 0) return new ArrayList<>(); 
            this.erronboMunizioa--;
        } else if (this.armaAktiboa instanceof TiroGeziEstrategia) {
        	System.out.println("Munición Gezi actual: " + this.geziMunizioa);
            if (this.geziMunizioa <= 0) {
            	System.out.println("¡SIN MUNICIÓN GEZI!");
            	return new ArrayList<>(); 
            }
            this.geziMunizioa--;
        }

        if (armaAktiboa != null && zentroa != null) {
            int absX = this.x + zentroa.getX();
            int absY = this.y + zentroa.getY();
            return armaAktiboa.tiroEgin(absX, absY - 1, 1);
        }
        return new ArrayList<>();
    }
}