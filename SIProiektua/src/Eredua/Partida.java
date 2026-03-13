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
		JokoKudeatzailea.getNireJK().setOntziKolorea(pKolorea);
		JokoKudeatzailea.getNireJK().hasiJokoa();
		
		this.jokoaHasiDa = true;
		
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
}