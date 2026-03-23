package Eredua;

public class Espaziontzia extends Entitatea {
	
	//Eraikitzailea
	public Espaziontzia(int x, int y) {
		super(x, y);
	}
	
	//Metodoak
	public void mugitu(String norabidea) {
		if (norabidea.equals("Eskumara")) {
			if (this.x < 99){
				this.x = this.x +1;
			}
		}else if (norabidea.equals("Ezkerrera")) {
			if (this.x > 0) {
				this.x = this.x -1;
			}
		}else if (norabidea.equals("Gora")) {
			if (this.y > 0) {
				this.y = this.y - 1;
			}
		}else if (norabidea.equals("Behera")) {
			if (this.y < 59) {
				this.y = this.y + 1;
			}
		}
	}
	
	@Override
	public void mugitu() {
		//Klase abstaktua eskatzen du
	}

	@Override
	public String getMota() {
		return "ESPAZIONTZIA";
	}

}
