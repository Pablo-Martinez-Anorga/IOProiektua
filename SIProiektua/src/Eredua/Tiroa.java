package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Tiroa extends Entitatea {
	
	//Eraikitzailea
	public Tiroa(int x, int y) {
        super(x, y);
    }

	@Override
	public Egoera getEgoeraObject() {
	    return new GelaxkaTiro();
	}
	
	@Override
	public void mugitu(String norabidea) {
		if (norabidea.equals("Gora")) this.y--;
	}
	
	@Override
	public boolean mugituDaiteke(String norabidea) {
	    // Tiroa pantailatik gora ateratzen bada, false itzuliko du
	    if (norabidea.equals("Gora")) return this.y > 0;
	    if (norabidea.equals("Behera")) return this.y < 59;
	    return true;
	}
}
