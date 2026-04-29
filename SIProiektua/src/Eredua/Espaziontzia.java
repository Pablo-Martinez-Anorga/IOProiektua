package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Espaziontzia extends Entitatea {

	public Espaziontzia(int x, int y) {
		super(x, y);
	}

	@Override
	public void mugitu() {
	}

	@Override
	public Egoera getEgoeraObject() {
		return new EspaziontziaEgoera();
	}

	//Monopixelaren offset-a itzuli (0,0)
	@Override
	public List<Entitatea> getPixelek() {
		List<Entitatea> pixelGuztiak = new ArrayList<>();
		pixelGuztiak.add(new Espaziontzia(0, 0)); 
		return pixelGuztiak;
	}
}