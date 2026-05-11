package Eredua;

public class EspaziontziaGorria extends EspaziontziNodo {
	
    public EspaziontziaGorria(int x, int y) {
        super(x, y); 
        this.setArma(new TiroPixelEstrategia());
    }

    @Override
    protected void itxuraSortu() {
        // Beheko lerroa (5 pixel)
        gehituOsagaia(new Espaziontzia(this.x - 2, this.y + 0));
        gehituOsagaia(new Espaziontzia(this.x - 1, this.y + 0));
        Entitatea zentro = new Espaziontzia(this.x + 0, this.y + 0);
        gehituOsagaia(zentro);
        gehituOsagaia(new Espaziontzia(this.x + 1, this.y + 0));
        gehituOsagaia(new Espaziontzia(this.x + 2, this.y + 0));

        // Erdiko lerroa (3 pixel)
        gehituOsagaia(new Espaziontzia(this.x - 1, this.y - 1));
        gehituOsagaia(new Espaziontzia(this.x + 0, this.y - 1));
        gehituOsagaia(new Espaziontzia(this.x + 1, this.y - 1));

        // Goiko lerroa / Antenak (2 pixel)
        gehituOsagaia(new Espaziontzia(this.x - 1, this.y - 2));
        gehituOsagaia(new Espaziontzia(this.x + 1, this.y - 2));

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