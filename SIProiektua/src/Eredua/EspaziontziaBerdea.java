package Eredua;

public class EspaziontziaBerdea extends Espaziontzia {
    public EspaziontziaBerdea(int x, int y) { 
    	super(x, y); 
    }

    @Override
    protected void itxuraSortu() {
        IrudiKonposatua irudia = this.getIrudia();
        irudia.gehituOsagaia(new Puntu(0, 0));
        irudia.gehituOsagaia(new Puntu(-1, 0));
        irudia.gehituOsagaia(new Puntu(1, 0));
        irudia.gehituOsagaia(new Puntu(0, -1));
        irudia.gehituOsagaia(new Puntu(0, 1));
    }

    @Override
    protected void aldatuArma() {
    	if (this.armaAktiboa instanceof TiroPixelEstrategia) {
            this.setArma(new TiroGeziEstrategia());
        } else {
            this.setArma(new TiroPixelEstrategia());
        }
    }

}
