package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Espaziontzia extends Entitatea {
	
	//Atributuak
	private String mota; // "RED", "GREEN", "BLUE"
	private List<TiroEstrategia> armak;
	private int armaAktiboaIndex;
	
	// Munizioa
	private int geziMunizioa;
	private int erronboMunizioa;
	
	//Eraikitzailea
	public Espaziontzia(int x, int y, String mota) { 
		super(x, y);
		this.mota = mota;
		this.armak = new ArrayList<>();
		this.armaAktiboaIndex = 0;
		
		// Munizioa hasieratu
		this.geziMunizioa = 30;
		this.erronboMunizioa = 20;
		
		// Ontziaren kolorearen arabera estrategiak kargatu
		armakKargatu();
	}
	
	//Metodoak
	private void armakKargatu() {
		// Lehenengo estrategia: Denek daukate pixel bakarrekoa (Infinitoa)
		this.armak.add(new TiroPixelEstrategia());
		
		// Bigarren estrategia: GREEN eta RED ontziek gezi tiroa dute (30 bala)
		if (this.mota.equals("GREEN") || this.mota.equals("RED")) {
			this.armak.add(new TiroGeziEstrategia());
		}
		
		// Hirugarren estrategia: BLUE eta RED ontziek erronbo tiroa dute (20 bala)
		if (this.mota.equals("BLUE") || this.mota.equals("RED")) {
			this.armak.add(new TiroErronboEstrategia());
		}
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

	public void aldatuArma() {
		this.armaAktiboaIndex = (this.armaAktiboaIndex + 1) % this.armak.size();
	}
	
	public List<Tiroa> tiroEgin() {
		TiroEstrategia unekoArma = this.armak.get(this.armaAktiboaIndex);
		
		// Munizioa kudeatu tiro egin aurretik
		if (unekoArma instanceof TiroGeziEstrategia) {
			if (this.geziMunizioa <= 0) return new ArrayList<>(); // Ez dago muniziorik
			this.geziMunizioa--;
		} else if (unekoArma instanceof TiroErronboEstrategia) {
			if (this.erronboMunizioa <= 0) return new ArrayList<>(); // Ez dago muniziorik
			this.erronboMunizioa--;
		}
		
		// Dena ondo badago, tiroa sortu
		return unekoArma.tiroEgin(this.x, this.y, 3);
	}
}