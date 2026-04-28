package Eredua;

public class Etsaia extends Entitatea {
	
	//Eraikitzailea
	public Etsaia(int x, int y) {
        super(x,y);
    }
	
	//Metodoak
	@Override
	public void mugitu() {
		int norabidea = (int)(Math.random() * 3); // 0 ezker, 1 eskuin, 2 behera
		boolean ezkerreraAhalDa = true;
		boolean eskumaraAhalDa = true;

		for (Entitatea p : this.getPixelek()) {
			if (this.x + p.getX() - 1 < 0) ezkerreraAhalDa = false;
			if (this.x + p.getX() + 1 >= 100) eskumaraAhalDa = false;
		}

		int xBerria = this.x;
		int yBerria = this.y;

		if (norabidea == 0 && ezkerreraAhalDa) {
			xBerria = this.x - 1;
		} else if (norabidea == 1 && eskumaraAhalDa) {
			xBerria = this.x + 1;
		} else {
			yBerria = this.y + 1;
		}

		if (Eredua.JokoKudeatzailea.getNireJK().posizioaLibreDa(xBerria, yBerria, this)) {
			this.x = xBerria;
			this.y = yBerria;
		}
	}

	@Override
	public Egoera getEgoeraObject() {
	    return new EtsaiaEgoera();
	}
}