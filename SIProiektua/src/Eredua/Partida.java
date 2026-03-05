package Eredua;

public class Partida {
	
	//Atributuak
	private boolean jokoaHasiDa;
	private boolean irabazita;
	private boolean galduta;
	
	//Eraikitzailea
	public Partida() {
		this.jokoaHasiDa = false;
		this.irabazita = false;
		this.galduta = false;
	}
	
	//Metodoak
	public void hasiJokoa() {
		this.jokoaHasiDa = true;
		this.irabazita = false;
		this.galduta = false;
	}
	
	public void amaituJokoa(boolean irabazi) {
		this.jokoaHasiDa = false;
		if (irabazi) {
            this.irabazita = true;
        } else {
            this.galduta = true;
        }
	}
	//JokoKudeatzaileak partidaaren egoera begiratzeko
	public boolean isJokoaHasiDa() {
		return this.jokoaHasiDa;
	}

	public boolean irabaziDuEgiaztatu() {
		return this.irabazita; 
	}

	public boolean galduDuEgiaztatu() {
		return this.galduta; 
	}
	
}
