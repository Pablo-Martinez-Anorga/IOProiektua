package Eredua;

import java.util.ArrayList;
import java.util.List;

public class Espaziontzia extends Entitatea {

	public Espaziontzia(int x, int y) {
		super(x, y);
	}

	@Override
	public void mugitu() {}

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
}