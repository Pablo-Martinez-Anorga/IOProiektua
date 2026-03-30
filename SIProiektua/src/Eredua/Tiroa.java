package Eredua;

public class Tiroa extends Entitatea {
	
	//Eraikitzailea
	public Tiroa(int x, int y) {
		// Pasamos x, y, ancho (1) y alto (2)
        super(x, y, 1, 2);
    }
	
	//Metodoak
	@Override
    public void mugitu() {
        this.y = this.y - 1;
	}

	@Override
	public String getMota() {
		return "TIROA";
	}
}