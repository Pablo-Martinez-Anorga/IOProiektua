package Eredua;

import java.util.Observable;

public class Partida extends Observable {
	
	// Singleton patroia
	private static Partida nirePartida = null;
	private boolean jokoaHasiDa;

	private Partida() {
		this.jokoaHasiDa = false;
	}

	public static Partida getNirePartida() {
		if (nirePartida == null) {
			nirePartida = new Partida();
		}
		return nirePartida;
	}

	// Jokoari hasiera eman
	public void hasiPartida(String pKolorea) {
		this.jokoaHasiDa = true;
		JokoKudeatzailea.getNireJK().setOntziKolorea(pKolorea);
		JokoKudeatzailea.getNireJK().hasiJokoa();
		
		// Bistari abisatu
		setChanged();
		notifyObservers("HASI");
	}

	// Jokoa amaitu eta egoera jakinarazi
	public void amaituJokoa(boolean irabazi) {
		this.jokoaHasiDa = false;
		setChanged();
		if (irabazi) {
			notifyObservers("IRABAZI");
		} else {
			notifyObservers("GALDU");
		}
	}

	public boolean isJokoaHasiDa() {
		return jokoaHasiDa;
	}
	
	public Gelaxka getGelaxka(int x, int y) {
		return JokoKudeatzailea.getNireJK().getGelaxka(x, y);
	}
	
	public String getOntziKolorea() {
		return JokoKudeatzailea.getNireJK().getOntziKolorea();
	}
	
	public void mugituOntzia(String norabidea) {
		JokoKudeatzailea.getNireJK().mugituOntzia(norabidea);
	}
	
	public void aldatuArma() {
		if (this.jokoaHasiDa) {
			JokoKudeatzailea.getNireJK().aldatuArma();
		}
	}
	
	public void tiroEgin() {
		if (this.jokoaHasiDa) {
			JokoKudeatzailea.getNireJK().tiroEgin();
		}
	}
}