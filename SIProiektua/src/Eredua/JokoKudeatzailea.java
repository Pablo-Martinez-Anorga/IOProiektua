package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.awt.Color; // Koloreak kudeatzeko beharrezkoa

public class JokoKudeatzailea extends Observable {
	
	// Atributu estatikoa (Singleton)
	private static JokoKudeatzailea nireJK = null;
	
	// Atributuak
	private Espaziontzia espaziontzia;
    private List<Etsaia> etsaiak;
    private List<Tiroa> tiroak;
    private Tableroa nireTableroa;
	private Partida unekoPartida;
	private Color ontziKolorea; // Jokalariak aukeratutako kolorea gordetzeko (E12)
	
	// Eraikitzailea
	private JokoKudeatzailea() {
		this.nireTableroa = new Tableroa();
		this.unekoPartida = new Partida();
		this.etsaiak = new ArrayList<>();
        this.tiroak = new ArrayList<>();
        this.espaziontzia = new Espaziontzia(50, 55); // Hasierako posizioa
	}
	
	// get metodoa (Singleton)
	public static JokoKudeatzailea getNireJK() {
		if (nireJK == null) {
			nireJK = new JokoKudeatzailea();
		}
		return nireJK;
	}
	
	// METODO BERRIAK (Zuk eskatutakoak)
	
	public void setOntziKolorea(Color pKolorea) {
	    this.ontziKolorea = pKolorea;
	}

	public Color getOntziKolorea() {
	    return this.ontziKolorea;
	}

	// EXISTITZEN DIREN METODOAK (Taldearen logika errespetatuz)
	
	public Tableroa getTableroa() {
		return this.nireTableroa;
	}
	
	public void hasiJokoa() {
		this.unekoPartida.hasiJokoa();
		etsaiakSortu(); // Etsaiak hasieratu jokoa hastean
		taulaEguneratu();
	}
	
	private void taulaEguneratu() {
		this.nireTableroa.garbituMatrizea();
		
		// Espaziontzia sartu
		this.nireTableroa.entitateaSartu(this.espaziontzia);
		
		// Etsaiak sartu
		for (Etsaia e : etsaiak) {
			this.nireTableroa.entitateaSartu(e);
		}
		
		// Tiroak sartu
		for (Tiroa t : tiroak) {
			this.nireTableroa.entitateaSartu(t);
		}
		
		setChanged();
		notifyObservers();
	}

	public void mugituOntzia(String norabidea) {
		this.espaziontzia.mugitu(norabidea);
		taulaEguneratu();
    }
	
	public void tiroEgin() {
		Tiroa berria = new Tiroa(this.espaziontzia.getX(), this.espaziontzia.getY() - 1);
        this.tiroak.add(berria);
        taulaEguneratu();
    }
	
	public void eguneratuEtsaiak() {
		for (Etsaia e : etsaiak) {
			e.mugitu();
		}
		taulaEguneratu();
    }
	
	public void etsaiakSortu() {
		// Sprint 1erako etsai batzuk sortu (adibidez 5. lerroan)
		for (int i = 0; i < 5; i++) {
			int x_pos = (int)(Math.random() * 100);
			this.etsaiak.add(new Etsaia(x_pos, 5));
		}
	}

	public boolean posizioaLibreDa(int x, int y) {
		return this.nireTableroa.getEntitatea(x, y) == null;
	}
}