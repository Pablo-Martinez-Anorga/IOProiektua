package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EspaziontziaUrdina extends EspaziontziNodo {

	private List<TiroEstrategia> armak;

	public EspaziontziaUrdina(int x, int y) {
        super(x, y);
        this.setArma(new TiroPixelEstrategia()); //Defektuzko arma ezarri
    }

	@Override
	protected void itxuraSortu() {
		// Oinarria
		this.gehituOsagaia(new Espaziontzia(0, 0));   // Erdigunea
		this.gehituOsagaia(new Espaziontzia(-1, 0));  // Ezkerra
		this.gehituOsagaia(new Espaziontzia(1, 0));   // Eskuina
		
		// URDINAREN pertsonalizazioa: T alderantzizkoa (Puntua goian)
		this.gehituOsagaia(new Espaziontzia(0, -1));  // Goian erdi-erdian
	}

	@Override
    public void aldatuArma() {
        if (this.armaAktiboa instanceof TiroPixelEstrategia) {
            this.setArma(new TiroErronboEstrategia());
        } else {
            this.setArma(new TiroPixelEstrategia());
        }
    }
}