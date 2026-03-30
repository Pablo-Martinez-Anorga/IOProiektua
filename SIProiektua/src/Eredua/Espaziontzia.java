package Eredua;

public class Espaziontzia extends Entitatea {
	
	//Eraikitzailea
	public Espaziontzia(int x, int y) {
		super(x, y, 5, 3);
	}
	
	//Metodoak
	public void mugitu(String norabidea) {
		if (norabidea.equals("Eskumara")) {
			if (this.x + this.zabalera < 100){
				this.x = this.x + 1;
			}
		}else if (norabidea.equals("Ezkerrera")) {
			if (this.x > 0) {
				this.x = this.x - 1;
			}
		}else if (norabidea.equals("Gora")) {
			if (this.y > 0) {
				this.y = this.y - 1;
			}
		}else if (norabidea.equals("Behera")) {
			if (this.y + this.altuera < 60) {
				this.y = this.y + 1;
			}
		}
	}
	
	@Override
	public void mugitu() {
	}

	@Override
	public String getMota() {
		return "ESPAZIONTZIA";
	}
}