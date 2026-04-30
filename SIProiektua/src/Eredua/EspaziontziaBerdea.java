package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EspaziontziaBerdea extends EspaziontziNodo {

	//private List<TiroEstrategia> armak;

	public EspaziontziaBerdea(int x, int y) {
        super(x, y);
        this.setArma(new TiroPixelEstrategia()); //Defektuzko arma
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
        if (this.armaAktiboa instanceof TiroPixelEstrategia) {
            this.setArma(new TiroGeziEstrategia());
        } else {
            this.setArma(new TiroPixelEstrategia());
        }
    }
}