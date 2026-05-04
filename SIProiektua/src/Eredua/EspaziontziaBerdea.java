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
		Entitatea erdia = new Espaziontzia(0, 0); 
        Entitatea goian = new Espaziontzia(0, -1);
        Entitatea behean = new Espaziontzia(0, 1);
        Entitatea ezkerrean = new Espaziontzia(-1, 0);
        Entitatea eskuinean = new Espaziontzia(1, 0);
        
        gehituOsagaia(erdia);
        gehituOsagaia(goian);
        gehituOsagaia(behean);
        gehituOsagaia(ezkerrean);
        gehituOsagaia(eskuinean);

        // Funtsezkoa tiroak nondik aterako diren jakiteko
        setZentroa(erdia);
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