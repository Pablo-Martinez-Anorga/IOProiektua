package Eredua;

public class Etsaia extends Entitatea {
	
	//Eraikitzailea
	public Etsaia(int x, int y) {
        super(x, y, 3, 3);
    }
	
	//Metodoak
	@Override
    public void mugitu() {
		int norabidea = (int)(Math.random() * 3);
		int xBerria = this.x;
	    int yBerria = this.y;
		//Ezkerrera
		if (norabidea == 0) {
			if (this.x > 0) {
				xBerria = this.x - 1;
			}
		}
		//Eskumara
		else if (norabidea == 1) {
			if (this.x + this.zabalera < 100) {
				xBerria = this.x + 1;
			}
		}
		//Behera
		else if (norabidea == 2) {
			if (this.y + this.altuera < 60) {
				yBerria = this.y + 1;
			}
		}
		//Libre badagoen begiratu
		if (Eredua.JokoKudeatzailea.getNireJK().posizioaLibreDa(xBerria, yBerria)) {
	        this.x = xBerria;
	        this.y = yBerria;
		}
	}

	@Override
	public String getMota() {
		return "ETSAIA";
	}
}