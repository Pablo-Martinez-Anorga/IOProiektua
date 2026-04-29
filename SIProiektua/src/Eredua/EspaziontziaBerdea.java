package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EspaziontziaBerdea extends EspaziontziNodo {

	private List<TiroEstrategia> armak;

	public EspaziontziaBerdea(int x, int y) {
		super(x, y);
		
		// Armak hasieratu (Pixel eta Gezi bakarrik)
		this.armak = new ArrayList<>();
		this.armak.add(new TiroPixelEstrategia());
		this.armak.add(new TiroGeziEstrategia());
		
		// Defektuzko arma ezarri
		this.setArma(this.armak.get(this.armaAktiboaIndex));
	}

	@Override
	protected void itxuraSortu() {
		// Oinarria
		this.gehituOsagaia(new Espaziontzia(0, 0));   // Erdigunea
		this.gehituOsagaia(new Espaziontzia(-1, 0));  // Ezkerra
		this.gehituOsagaia(new Espaziontzia(1, 0));   // Eskuina
		
		// BERDEAREN pertsonalizazioa: Gurutzea (Pixel bat goian eta beste bat behean)
		this.gehituOsagaia(new Espaziontzia(0, -1));  // Goian
		this.gehituOsagaia(new Espaziontzia(0, 1));   // Behean
	}

	@Override
	public void aldatuArma() {
		// % erabiliz, zerrendako 2 armen artean txandakatzen du (0 eta 1)
		this.armaAktiboaIndex = (this.armaAktiboaIndex + 1) % this.armak.size();
		this.setArma(this.armak.get(this.armaAktiboaIndex));
	}
}