package Eredua;

public class EspaziontziaGorria extends EspaziontziNodo {
	
    public EspaziontziaGorria(int x, int y) {
        super(x, y); 
        this.setArma(new TiroPixelEstrategia());
    }

    @Override
    protected void itxuraSortu() {
    	Entitatea ezker = new Espaziontzia(this.x - 1, this.y + 0);
        Entitatea zentro = new Espaziontzia(this.x + 0, this.y + 0); 
        Entitatea eskuin = new Espaziontzia(this.x + 1, this.y + 0);
        Entitatea punta = new Espaziontzia(this.x + 0, this.y - 1);

        gehituOsagaia(ezker);
        gehituOsagaia(zentro);
        gehituOsagaia(eskuin);
        gehituOsagaia(punta);

        setZentroa(zentro); 
    }

    @Override
    public void aldatuArma() {
    	if (this.armaAktiboa instanceof TiroPixelEstrategia) {
            this.setArma(new TiroErronboEstrategia());
        } else if (this.armaAktiboa instanceof TiroErronboEstrategia) {
            this.setArma(new TiroGeziEstrategia());
        } else {
            this.setArma(new TiroPixelEstrategia());
        }
    }
}