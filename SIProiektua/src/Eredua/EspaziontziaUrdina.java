package Eredua;

public class EspaziontziaUrdina extends Espaziontzia {
    public EspaziontziaUrdina(int x, int y) { 
    	super(x, y); 
    }

    @Override
    protected void itxuraSortu() {
        IrudiKonposatua irudia = this.getIrudia();
        irudia.gehituOsagaia(new Puntu(0, 0));
        irudia.gehituOsagaia(new Puntu(-1, 0));
        irudia.gehituOsagaia(new Puntu(1, 0));
        irudia.gehituOsagaia(new Puntu(0, -1));
    }

    @Override
    protected void aldatuArma() {
    	if (this.armaAktiboa instanceof TiroPixelEstrategia) {
            this.setArma(new TiroErronboEstrategia());
        } else {
            this.setArma(new TiroPixelEstrategia());
        }
    }

}
