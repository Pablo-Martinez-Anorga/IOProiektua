package Eredua;

public class Partida {
	
	//Atributuak
	private boolean jokoaHasiDa;
	private boolean irabazita;
	
	//Eraikitzailea
	public Partida() {
		this.jokoaHasiDa = false;
		this.irabazita = false;
	}
	
	//Metodoak
	public void hasiJokoa() {
		this.jokoaHasiDa = true;
	}
	
	public void amaituJokoa() {
		this.jokoaHasiDa = false;
	}
	//JokoKudeatzaileak partidaaren egoera begiratzeko
	public boolean isJokoaHasiDa() {
		return this.jokoaHasiDa;
	}

	public boolean irabaziDuEgiaztatu() {
		return this.irabazita; 
	}

	public boolean galduDuEgiaztatu() {
		return false; 
	}
	
}
