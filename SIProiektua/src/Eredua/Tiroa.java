package Eredua;

public class Tiroa extends Entitatea {
	
	//Eraikitzailea
	public Tiroa(int x, int y) {
        super(x, y);
        this.irudia.gehituOsagaia(new Puntu(0, 0));
    }
	
	//Metodoak
	@Override
    public void mugitu() {
        this.y = this.y - 1;
	}

	@Override
	public Egoera getEgoeraObject() {
	    return new TiroaEgoera();
	}
}