package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EspaziontziaGorria extends EspaziontziNodo {

	// Ontzi gorriaren arma-zerrenda (Strategy patroia)
	private List<TiroEstrategia> armak;

	public EspaziontziaGorria(int x, int y) {
        super(x, y);
        // Hasieraketa defektuzko armarekin
        this.setArma(new TiroPixelEstrategia());
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
		this.gehituOsagaia(new Espaziontzia(-2, 0));
		this.gehituOsagaia(new Espaziontzia(2, 0));
	}

	@Override
    public void aldatuArma() {
        // Lógica de intercambio usando instanceof
        if (this.armaAktiboa instanceof TiroPixelEstrategia) {
            this.setArma(new TiroGeziEstrategia());
        } else if (this.armaAktiboa instanceof TiroGeziEstrategia) {
            this.setArma(new TiroErronboEstrategia());
        } else {
            this.setArma(new TiroPixelEstrategia());
        }
    }
}