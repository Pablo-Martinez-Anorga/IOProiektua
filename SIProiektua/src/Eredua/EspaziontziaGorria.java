package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EspaziontziaGorria extends EspaziontziNodo {

	// Ontzi gorriaren arma-zerrenda (Strategy patroia)
	private List<TiroEstrategia> armak;

	public EspaziontziaGorria(int x, int y) {
		super(x, y);
		
		// Armak hasieratu
		this.armak = new ArrayList<>();
		this.armak.add(new TiroPixelEstrategia());
		this.armak.add(new TiroGeziEstrategia());
		this.armak.add(new TiroErronboEstrategia());
		
		// Defektuzko arma ezarri (Index 0 = Pixel)
		this.setArma(this.armak.get(this.armaAktiboaIndex));
	}

	@Override
	protected void itxuraSortu() {
		// Oinarria (Denentzat berdina zena)
		this.gehituOsagaia(new Espaziontzia(0, 0));   // Erdigunea
		this.gehituOsagaia(new Espaziontzia(-1, 0));  // Ezkerra
		this.gehituOsagaia(new Espaziontzia(1, 0));   // Eskuina
		
		// GORRIAREN pertsonalizazioa: U forma (Goiko bi pixelak)
		this.gehituOsagaia(new Espaziontzia(-1, -1)); // Goian ezkerrean
		this.gehituOsagaia(new Espaziontzia(1, -1));  // Goian eskuinean
	}

	@Override
	public void aldatuArma() {
		// % (modulua) erabiliz, indizea 0, 1 eta 2 artean ibiliko da begiztan
		this.armaAktiboaIndex = (this.armaAktiboaIndex + 1) % this.armak.size();
		
		// Arma berria aktibatu
		this.setArma(this.armak.get(this.armaAktiboaIndex));
	}
}