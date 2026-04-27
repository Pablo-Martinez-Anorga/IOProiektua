package Eredua;

import java.util.ArrayList;
import java.util.List;

public abstract class Espaziontzia extends Entitatea {
	
	//Atributuak  
	protected TiroEstrategia armaAktiboa;
	protected int armaAktiboaIndex;
	// Munizioa
	protected int geziMunizioa;
	protected int erronboMunizioa;
	
	//Eraikitzailea
	public Espaziontzia(int x, int y) { 
		super(x, y);
		this.armaAktiboaIndex = 0;
		
		// Munizioa hasieratu
		this.geziMunizioa = 30;
		this.erronboMunizioa = 20;
		
		itxuraSortu();
		
		this.armaAktiboa = new TiroPixelEstrategia();
	}
	
	//Metodoak
	protected abstract void itxuraSortu();
	protected abstract void aldatuArma();
	
	public void setArma(TiroEstrategia armaBerria) {
		this.armaAktiboa = armaBerria;
	}
	
	public void mugitu(String norabidea) {
	    boolean ezkerreraAhalDa = true;
	    boolean eskumaraAhalDa = true;
	    boolean goraAhalDa = true;
	    boolean beheraAhalDa = true;
	    
	    for (Puntu p : this.getPixelek()) {
	        if (this.x + p.getDx() - 1 < 0) ezkerreraAhalDa = false;
	        if (this.x + p.getDx() + 1 >= 100) eskumaraAhalDa = false;
	        if (this.y + p.getDy() - 1 < 0) goraAhalDa = false;
	        if (this.y + p.getDy() + 1 >= 60) beheraAhalDa = false;
	    }

	    if (norabidea.equals("Eskumara") && eskumaraAhalDa) this.x += 1;
	    else if (norabidea.equals("Ezkerrera") && ezkerreraAhalDa) this.x -= 1;
	    else if (norabidea.equals("Gora") && goraAhalDa) this.y -= 1;
	    else if (norabidea.equals("Behera") && beheraAhalDa) this.y += 1;
	}
	
	@Override
	public void mugitu() {
	}

	@Override
	public Egoera getEgoeraObject() {
	    return new EspaziontziaEgoera();
    }
	
	public List<Tiroa> tiroEgin() {
		if (this.armaAktiboa instanceof TiroGeziEstrategia) {
			if (this.geziMunizioa <= 0) return new ArrayList<>(); // Ez dago muniziorik
			this.geziMunizioa--;
		} else if (this.armaAktiboa instanceof TiroErronboEstrategia) {
			if (this.erronboMunizioa <= 0) return new ArrayList<>(); // Ez dago muniziorik
			this.erronboMunizioa--;
		}
		
		// Dena ondo badago, tiroa sortu
		return this.armaAktiboa.tiroEgin(this.x, this.y, 3);
	}
}