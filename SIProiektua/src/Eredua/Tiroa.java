package Eredua;

public class Tiroa extends Entitatea {
	
	//Eraikitzailea
	public Tiroa(int x, int y) {
        super(x, y);
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