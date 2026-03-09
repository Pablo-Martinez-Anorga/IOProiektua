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

	private void etsaiakSortu() {
		int y_posizioa = 5;
		//Zerrenda garbitu
		this.etsaiak.clear();
		//4-8 tarteko etsaiak sortu
		int etsaiKopurua = (int)(Math.random() * 5) + 4;
		ArrayList<Integer> erabilitakoX = new ArrayList<>();
		for (int i = 0; i < etsaiKopurua; i++) {
			//Posizioa esleitu
			int x_posizioa = (int)(Math.random() * 100); 
			//Pozizioa erabilita badago beste bat esleitu
			while (erabilitakoX.contains(x_posizioa)) {
				x_posizioa = (int)(Math.random() * 100); 
			}       
			erabilitakoX.add(x_posizioa);
			Etsaia etsaiBerria = new Etsaia(x_posizioa, y_posizioa);
			this.etsaiak.add(etsaiBerria);
		}
		taulaEguneratu();
	}
		


	public void mugituOntzia(String norabidea) {
		this.espaziontzia.mugitu(norabidea);
		taulaEguneratu();
    }
	
	public void tiroEgin() {
		Tiroa berria = new Tiroa(this.espaziontzia.getX(), this.espaziontzia.getY() - 2);
        this.tiroak.add(berria);
        taulaEguneratu();
    }
	
	public void eguneratuEtsaiak() {
		for (Etsaia e : etsaiak) {
			e.mugitu();
		}
		talkakEgiaztatu();
		jokoEgoeraEgiaztatu();
		taulaEguneratu();
    }
	
	public void talkakEgiaztatu() {
		// 1. Tiroak vs Etsaiak 
		for (int i = tiroak.size() - 1; i >= 0; i--) {
	        Tiroa t = tiroak.get(i);
	        boolean tiroakAsmatuDu = false;
	        int j = etsaiak.size() - 1;
	        
	        while (j >= 0 && !tiroakAsmatuDu) {
	            Etsaia e = etsaiak.get(j);
	            if (t.getX() == e.getX() && t.getY() == e.getY()) {
	                etsaiak.remove(j); // "Etsaiak espaziontziaren tiroekin suntsituko dira."
	                tiroakAsmatuDu = true; 
	            }
	            j--;
	        }
	        
	        if (tiroakAsmatuDu) {
	            tiroak.remove(i);
	        }
	    }

	    // 2. Etsaiak vs Espaziontzia
	    for (int i = 0; i < etsaiak.size(); i++) {
	        Etsaia e = etsaiak.get(i);
	        if (e.getX() == espaziontzia.getX() && e.getY() == espaziontzia.getY()) {
	            unekoPartida.amaituJokoa(false); // Amaitu jokoa
	            System.out.println("¡Etsai batek ukitu zaitu! Galdu duzu.");
	            // "Game Over" bistaratu behar
	        }
	    }
	    jokoEgoeraEgiaztatu();
    }
	
	public void jokoEgoeraEgiaztatu() {
	    // IRABAZI
	    if (etsaiak.isEmpty() && unekoPartida.isJokoaHasiDa()) {
	        unekoPartida.amaituJokoa(true); 
	        System.out.println("ZORIONAK! Irabazi duzu.");
	    }
	    
	    // GALDU
	    if (unekoPartida.isJokoaHasiDa()) {
	        boolean inbasioa = false;
	        int i = 0;
	        while (i < etsaiak.size() && !inbasioa) {
	            if (etsaiak.get(i).getY() >= 59) { 
	                unekoPartida.amaituJokoa(false); 
	                System.out.println("GALDU DUZU. Etsaiek matrizearen azpitik alde egin dute.");
	                inbasioa = true;
	            }
	            i++;
	        }
	    }
	}
	

	public boolean posizioaLibreDa(int x, int y) {
		return this.nireTableroa.getEntitatea(x, y) == null;
	}

}
