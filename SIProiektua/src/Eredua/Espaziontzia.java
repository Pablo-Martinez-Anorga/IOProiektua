package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Espaziontzia extends Entitatea {

	public Espaziontzia(int x, int y) {
		super(x, y);
	}

	@Override
	public Egoera getEgoeraObject() {
		return new GelaxkaEspaziontzi();
	}

	@Override
	public void mugitu(String norabidea) {
		if (norabidea.equals("Eskumara")) this.x++;
        else if (norabidea.equals("Ezkerrera")) this.x--;
        else if (norabidea.equals("Gora")) this.y--;
        else if (norabidea.equals("Behera")) this.y++;
	}
	
	@Override
	public boolean mugituDaiteke(String norabidea) {
	    int nx = this.x;
	    int ny = this.y;
	    
	    if (norabidea.equals("Ezkerrera")) nx--;
	    else if (norabidea.equals("Eskumara")) nx++;
	    else if (norabidea.equals("Gora")) ny--;
	    else if (norabidea.equals("Behera")) ny++;

	    return (nx >= 0 && nx < 100 && ny >= 0 && ny < 60);
	}
}