package Eredua;

import java.util.Observable;

public class Tableroa extends Observable{
	
	//Atributuak
	private Entitatea[][] laukiak;
	
	//Eraikitzailea
	public Tableroa() {
		this.laukiak = new Entitatea[100][60];
	}
	
	//Metodoak
	public void garbituMatrizea() {
		this.laukiak = new Entitatea[100][60];
	}
	
	public void entitateaSartu(Entitatea e) {
		if (e != null && e.getX() >= 0 && e.getX() < 100 && e.getY() >= 0 && e.getY() < 60) {
			this.laukiak[e.getX()][e.getY()] = e;
		}
	}
	
	public void entitateaKendu(Entitatea e) {
		if (e != null && e.getX() >= 0 && e.getX() < 100 && e.getY() >= 0 && e.getY() < 60) {
			this.laukiak[e.getX()][e.getY()] = null;
		}
	}
	
	public Entitatea getEntitatea(int x, int y) {
		return this.laukiak[x][y];
	}
	
	public Entitatea[][] getLaukiak() {
		return this.laukiak;
	}
	
	public void bistaEguneratu() {
		setChanged();
		notifyObservers();
	}

}
