package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Tiroa extends Entitatea {
	
	//Eraikitzailea
	public Tiroa(int x, int y) {
        super(x, y);
    }
	
	//Metodoak
	@Override
    public void mugitu() {
		this.y--;
	}

	@Override
	public Egoera getEgoeraObject() {
	    return new GelaxkaTiro();
	}
	
	@Override
	public void mugitu(String norabidea) {
		if (norabidea.equals("Gora")) this.y--;
	}
}
