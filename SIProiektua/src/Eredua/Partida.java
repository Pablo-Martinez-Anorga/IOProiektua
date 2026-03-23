package Eredua;

import java.util.Observable; 

public class Partida extends Observable {
	
	// Singleton
	private static Partida nirePartida = null;
	
	private boolean jokoaHasiDa;
	private boolean irabazita;
	private boolean galduta;
	
	private Partida() {
		this.jokoaHasiDa = false;
		this.irabazita = false;
		this.galduta = false;
	}
	
	public static Partida getNirePartida() {
		if (nirePartida == null) {
			nirePartida = new Partida();
		}
		return nirePartida;
	}
	
	// --- KONTROLADOREEKIN KOMUNIKATZEKO METODOAK --- //

	public void hasiJokoa(String kolorea) {
		JokoKudeatzailea.getNireJK().setOntziKolorea(kolorea);
		this.jokoaHasiDa = true;
		this.irabazita = false;
		this.galduta = false;
		JokoKudeatzailea.getNireJK().hasiJokoa();
		setChanged();
		notifyObservers("HASI");	
	}
	
	public void mugituOntzia(String norabidea) {
		if (this.jokoaHasiDa) {
			JokoKudeatzailea.getNireJK().mugituOntzia(norabidea);
		}
	}
	
	public void tiroEgin() {
		if (this.jokoaHasiDa) {
			JokoKudeatzailea.getNireJK().tiroEgin();
		}
	}

	// --- BUKAERA KUDEATZEKO --- //
	
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
	
	public Gelaxka getGelaxka(int x, int y) {
		return JokoKudeatzailea.getNireJK().getGelaxka(x, y);
	}
	
	public String getOntziKolorea() {
		return JokoKudeatzailea.getNireJK().getOntziKolorea();
	}
	
	public boolean isJokoaHasiDa() { return this.jokoaHasiDa; }
	public boolean irabaziDuEgiaztatu() { return this.irabazita; }
	public boolean galduDuEgiaztatu() { return this.galduta; }
}