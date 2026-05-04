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
		Entitatea erdia = new Espaziontzia(this.x + 0, this.y + 0); 
        Entitatea goian = new Espaziontzia(this.x + 0, this.y - 1);
        Entitatea behean = new Espaziontzia(this.x + 0, this.y + 1);
        Entitatea ezkerrean = new Espaziontzia(this.x - 1, this.y + 0);
        Entitatea eskuinean = new Espaziontzia(this.x + 1, this.y + 0);
        
        gehituOsagaia(erdia);
        gehituOsagaia(goian);
        gehituOsagaia(behean);
        gehituOsagaia(ezkerrean);
        gehituOsagaia(eskuinean);

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