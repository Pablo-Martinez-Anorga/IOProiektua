package Eredua;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class Partida extends Observable {
	
	// Singleton patroia
	private static Partida nirePartida = null;
	private boolean jokoaHasiDa;
	
	// Jokalariak kudeatzeko atributuak berriak
	private List<Jokalaria> jokalariak;
	private Jokalaria unekoJokalaria;

	private Partida() {
		this.jokoaHasiDa = false;
		this.jokalariak = new ArrayList<>(); // Zerrenda hasieratu
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
		setChanged();
		notifyObservers("HASI");
		JokoKudeatzailea.getNireJK().hasiJokoa();
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

	// Hasierako pantailatik deituko da jokoa hasi baino lehen
	public void ezarriUnekoJokalaria(String izena) {
		this.unekoJokalaria = new Jokalaria(izena);
		this.jokalariak.add(this.unekoJokalaria);
	}
	
	// JokoKudeatzailetik etsai bat hiltzean
	public void gehituPuntuak(int puntuak) {
		if (this.unekoJokalaria != null) {
			this.unekoJokalaria.gehituPuntuak(puntuak);
		}
	}
	
	// Java 8: Stream erabiliz Top 5 jokalariak lortzeko
	public String getTopJokalariak() {
		if (jokalariak.isEmpty()) {
			return "Ez dago jokalaririk oraindik.";
		}

		// Ordenatu, mugatu 5era, String bihurtu eta elkartu (\n erabiliz)
		return jokalariak.stream().sorted(Comparator.comparingInt(Jokalaria::getPuntuazioa).reversed()).limit(5).map(j -> j.getIzena() + " - " + j.getPuntuazioa() + " puntu").collect(Collectors.joining("\n"));
	}

	// --- GETTER ETA DELEGATUAK ---

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