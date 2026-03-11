package Eredua;

import java.util.Observable; 

public class Partida extends Observable {
	
	private boolean jokoaHasiDa;
	private boolean irabazita;
	private boolean galduta;
	
	public Partida() {
		this.jokoaHasiDa = false;
		this.irabazita = false;
		this.galduta = false;
	}
	
	public void hasiJokoa() {
		this.jokoaHasiDa = true;
		this.irabazita = false;
		this.galduta = false;
	}
	
	public void etsaiKopuruaEguneratu(int etsaiKopurua) {
		if (this.jokoaHasiDa && etsaiKopurua == 0) {
			amaituJokoa(true);
		}
	}
	
	public void amaituJokoa(boolean irabazi) {
		if (!this.jokoaHasiDa) return; 
		
		this.jokoaHasiDa = false;
		if (irabazi) {
            this.irabazita = true;
            setChanged();
            notifyObservers("IRABAZI");
        } else {
            this.galduta = true;
            setChanged();
            notifyObservers("GALDU"); 
        }
	}
	
	public boolean isJokoaHasiDa() { return this.jokoaHasiDa; }
	public boolean irabaziDuEgiaztatu() { return this.irabazita; }
	public boolean galduDuEgiaztatu() { return this.galduta; }
}