package Eredua;

import java.util.ArrayList;
import java.util.List;

public class EspaziontziaUrdina extends EspaziontziNodo {

	//private List<TiroEstrategia> armak;

	public EspaziontziaUrdina(int x, int y) {
        super(x, y);
        this.setArma(new TiroPixelEstrategia()); //Defektuzko arma ezarri
    }

	@Override
	protected void itxuraSortu() {
		Entitatea zentro = new Espaziontzia(0, 0); 
        Entitatea ezker_behe = new Espaziontzia(-1, 1);
        Entitatea eskuin_behe = new Espaziontzia(1, 1);
        
        // Gehitu
        gehituOsagaia(zentro);
        gehituOsagaia(ezker_behe);
        gehituOsagaia(eskuin_behe);

        // Zentroa gorde, bestela ez du tiro egingo! 🔥
        setZentroa(zentro);
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