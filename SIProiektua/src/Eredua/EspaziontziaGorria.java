package Eredua;

public class EspaziontziaGorria extends EspaziontziNodo {
	
    public EspaziontziaGorria(int x, int y) {
        super(x, y); 
        this.setArma(new TiroPixelEstrategia());
    }

    @Override
    protected void itxuraSortu() {
        Entitatea ezker = new Espaziontzia(-1, 0);
        Entitatea zentro = new Espaziontzia(0, 0); 
        Entitatea eskuin = new Espaziontzia(1, 0);
        Entitatea punta = new Espaziontzia(0, -1);

        gehituOsagaia(ezker);
        gehituOsagaia(zentro);
        gehituOsagaia(eskuin);
        gehituOsagaia(punta);

        setZentroa(zentro); 
    }

    @Override
    public void aldatuArma() {
        // Al no haber variable local, esto cambia la variable del padre
    	if (this.armaAktiboa instanceof TiroPixelEstrategia) {
            this.armaAktiboa = new TiroErronboEstrategia();
        } else if (this.armaAktiboa instanceof TiroErronboEstrategia) {
            this.armaAktiboa = new TiroGeziEstrategia();
        } else {
            this.armaAktiboa = new TiroPixelEstrategia();
        }
    }
}