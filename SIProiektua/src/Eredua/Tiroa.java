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
        this.y = this.y - 1;
	}

	@Override
	public Egoera getEgoeraObject() {
	    return new GelaxkaTiro();
	}
	
	@Override
	public void mugitu(String norabidea) {
	    if (norabidea.equals("Gora")) {
	        this.y--; // Pantailan gora joateko Y koordenatua txikitu behar da
	    } else if (norabidea.equals("Behera")) {
	        this.y++; // Pantailan behera joateko Y koordenatua handitu behar da
	    }
	}
	
	// GAKOA COMPOSITE-RAKO: Monopixelaren offset-a (0,0) itzuli behar da beti.
	/*@Override
	public List<Entitatea> getPixelek() {
	    List<Entitatea> pixelak = new ArrayList<>();
	    pixelak.add(new Tiroa(0, 0)); 
	    return pixelak;
	}
	*/
}