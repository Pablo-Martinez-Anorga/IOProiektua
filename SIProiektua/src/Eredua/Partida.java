package Eredua;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class Partida extends Observable {
	
	private static Partida nirePartida = null;
	private boolean jokoaHasiDa;
	
	private List<Jokalaria> jokalariak;
	private Jokalaria unekoJokalaria;

	private Partida() {
		this.jokoaHasiDa = false;
		this.jokalariak = new ArrayList<>();
	}

	public static Partida getNirePartida() {
		if (nirePartida == null) {
			nirePartida = new Partida();
		}
		return nirePartida;
	}

	public void hasiPartida(String pKolorea) {
		this.jokoaHasiDa = true;
		JokoKudeatzailea.getNireJK().setOntziKolorea(pKolorea);
		setChanged();
		notifyObservers("PUNTUAK_ALDATU");
		setChanged();
		notifyObservers("HASI");
		JokoKudeatzailea.getNireJK().hasiJokoa();
	}

	public void amaituJokoa(boolean irabazi) {
		this.jokoaHasiDa = false;
		setChanged();
		if (irabazi) {
			notifyObservers("IRABAZI");
		} else {
			notifyObservers("GALDU");
		}
	}

	// --- JOKALARIEN ETA PUNTUEN KUDEAKETA ---
	
	public void ezarriUnekoJokalaria(String izena) {
		this.unekoJokalaria = new Jokalaria(izena);
		this.jokalariak.add(this.unekoJokalaria);
	}
	
	public void gehituPuntuak(int puntuak) {
		if (this.unekoJokalaria != null) {
			this.unekoJokalaria.gehituPuntuak(puntuak);
			setChanged();
			notifyObservers("PUNTUAK_ALDATU"); // UI-ari abisatu puntuak aldatu direla
		}
	}
	
	public int getUnekoPuntuazioa() {
		return (this.unekoJokalaria != null) ? this.unekoJokalaria.getPuntuazioa() : 0;
	}
	
	public String getTopJokalariak() {
		if (jokalariak.isEmpty()) return "Ez dago jokalaririk oraindik.";

		return jokalariak.stream()
				.sorted(Comparator.comparingInt(Jokalaria::getPuntuazioa).reversed())
				.limit(5)
				.map(j -> j.getIzena() + " - " + j.getPuntuazioa() + " puntu")
				.collect(Collectors.joining("\n"));
	}

	// --- GETTER ETA DELEGATUAK ---
	public boolean isJokoaHasiDa() { return jokoaHasiDa; }
	public Gelaxka getGelaxka(int x, int y) { return JokoKudeatzailea.getNireJK().getGelaxka(x, y); }
	public String getOntziKolorea() { return JokoKudeatzailea.getNireJK().getOntziKolorea(); }
	public void mugituOntzia(String norabidea) { JokoKudeatzailea.getNireJK().mugituOntzia(norabidea); }
	
	public void aldatuArma() {
		if (this.jokoaHasiDa) JokoKudeatzailea.getNireJK().aldatuArma();
	}
	
	public void tiroEgin() {
		if (this.jokoaHasiDa) JokoKudeatzailea.getNireJK().tiroEgin();
	}
}