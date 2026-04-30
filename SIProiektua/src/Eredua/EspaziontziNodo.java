package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class EspaziontziNodo extends Entitatea {
	
	// COMPOSITE: Entitate bilduma
	protected List<Entitatea> osagaiak;

	// Strategy 
	protected TiroEstrategia armaAktiboa;

	
	// Munizioa
	protected int geziMunizioa;
	protected int erronboMunizioa;
	
	// Eraikitzailea
	public EspaziontziNodo(int x, int y) { 
		super(x, y);
		this.osagaiak = new ArrayList<>(); //itxuraSortu baino lehen hasieratu
		
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

	@Override
	public List<Entitatea> getPixelek() {
		return osagaiak.stream().flatMap(e -> e.getPixelek().stream().map(p -> new Espaziontzia(e.getX() + p.getX(), e.getY() + p.getY()))).collect(Collectors.toList());
	}
	
	// Metodoak
	protected abstract void itxuraSortu();
	public abstract void aldatuArma();
	
	public void setArma(TiroEstrategia armaBerria) {
		this.armaAktiboa = armaBerria;
	}
	
	public void mugitu(String norabidea) {
		// Java 8: for guztiak kendu noneMatch erabili
		boolean ezkerreraAhalDa = this.getPixelek().stream().noneMatch(p -> this.x + p.getX() - 1 < 0);
		boolean eskumaraAhalDa = this.getPixelek().stream().noneMatch(p -> this.x + p.getX() + 1 >= 100);
		boolean goraAhalDa = this.getPixelek().stream().noneMatch(p -> this.y + p.getY() - 1 < 0);
		boolean beheraAhalDa = this.getPixelek().stream().noneMatch(p -> this.y + p.getY() + 1 >= 60);

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
	
	//List<Entitatea> itzultzen du, TiroFaktoriarekin bat egiteko
	public List<Entitatea> tiroEgin() {
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