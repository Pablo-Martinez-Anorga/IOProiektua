package Eredua;

public class Etsaia extends Entitatea {
	
	//Eraikitzailea
	public Etsaia(int x, int y) {
        super(x, y);
    }
	
	//Metodoak
	@Override
    public void mugitu() {
        this.y = this.y + 1;
    }

}
