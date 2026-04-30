package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JokoKudeatzailea { 
	
	private static JokoKudeatzailea nireJK = null;
	
	private EspaziontziNodo espaziontzia;
	private List<Entitatea> etsaiak;
	private List<Entitatea> tiroak;
	private String ontziKolorea; 
	private Gelaxka[][] gelaxkak;
	
	private JokoKudeatzailea() {
		this.etsaiak = new ArrayList<>();
		this.tiroak = new ArrayList<>();
		// FACTORY PATROIA: Defektuzko ontzia sortu (Gero hasiJokoa-n aldatuko da)
		this.espaziontzia = EspaziontziaFaktoria.getNireFaktoria().sortuEspaziontzia("GREEN", 50, 55);
		this.gelaxkak = new Gelaxka[100][60];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 60; j++) {
				this.gelaxkak[i][j] = new Gelaxka(i, j);
			}
		}
	}
	
	public static JokoKudeatzailea getNireJK() {
		if (nireJK == null) {
			nireJK = new JokoKudeatzailea();
		}
		return nireJK;
	}
	
	public void setOntziKolorea(String pKolorea) { this.ontziKolorea = pKolorea; }
	public String getOntziKolorea() { return this.ontziKolorea; }

	public Gelaxka getGelaxka(int x, int y) {
		return this.gelaxkak[x][y];
	}
	
	public void hasiJokoa() {
		// Kolorearen araberako ontzia sortu
		this.espaziontzia = EspaziontziaFaktoria.getNireFaktoria().sortuEspaziontzia(this.ontziKolorea, 50, 55);
		
		etsaiakSortu(); 
		//TIMER-AK HIL BEHAR DIRA
		Timer tiroenTimer = new Timer();
		tiroenTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (Partida.getNirePartida().isJokoaHasiDa()) {
					eguneratuTiroak(); 		
				} else {
					this.cancel();
				}
			}
		}, 0, 50);
		
		Timer etsaienTimer = new Timer();
		etsaienTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (Partida.getNirePartida().isJokoaHasiDa()) {
					eguneratuEtsaiak(); 
				} else {
					this.cancel();
				}
			}
		}, 0, 200);
		
		taulaEguneratu();
	}
	
	private synchronized void taulaEguneratu() { 
	    Egoera[][] matrizVirtual = new Egoera[100][60];
	    for (int i = 0; i < 100; i++) {
	        for (int j = 0; j < 60; j++) {
	            matrizVirtual[i][j] = new HutsaEgoera();
	        }
	    }

	    markatuMatrizean(matrizVirtual, this.espaziontzia);
	    for (Entitatea e : etsaiak) { markatuMatrizean(matrizVirtual, e); }
	    for (Entitatea t : tiroak) { markatuMatrizean(matrizVirtual, t); }

	    for (int i = 0; i < 100; i++) {
	        for (int j = 0; j < 60; j++) {
	            String unekoIzena = this.gelaxkak[i][j].getEgoera();
	            String berriaIzena = matrizVirtual[i][j].getIzena();
	            
	            if (!unekoIzena.equals(berriaIzena)) {
	                this.gelaxkak[i][j].egoeraAldatu(matrizVirtual[i][j]);
	            }
	        }
	    }
	}
	
	private void markatuMatrizean(Egoera[][] matrizVirtual, Entitatea e) {
		if (e != null) {
			int ex = e.getX();
			int ey = e.getY();
			for (Entitatea p : e.getPixelek()) {
				int nx = ex + p.getX();
				int ny = ey + p.getY();
				if (nx >= 0 && nx < 100 && ny >= 0 && ny < 60) {
					matrizVirtual[nx][ny] = e.getEgoeraObject();
				}
			}
		}
	}

	private void etsaiakSortu() {
	    int y_posizioa = 10;
	    this.etsaiak.clear();
	    int etsaiKopurua = (int)(Math.random() * 5) + 4;
	    ArrayList<Integer> erabilitakoX = new ArrayList<>();
	    
	    for (int i = 0; i < etsaiKopurua; i++) {
	        int x_posizioa = 0; 
	        boolean posizioaValida = false;
	        
	        while (!posizioaValida) {
	        	// Posizio berri bat probatu
	        	int probaX = (int)(Math.random() * 93) + 3;
	            
	        	// Java 8: Posizioa baliozkoa den jakiteko (dist > 5) noneMatch-en bidez
	        	posizioaValida = erabilitakoX.stream().noneMatch(x_existente -> Math.abs(x_existente - probaX) < 5);
	            
	        	if (posizioaValida) {
	        		x_posizioa = probaX;
	        	}
	        }
	        
	        erabilitakoX.add(x_posizioa);
	        this.etsaiak.add(EtsaiaFaktoria.getNireFaktoria().sortuEtsaia(x_posizioa, y_posizioa, "MULTIPIXEL"));
	    }
	    taulaEguneratu();
	}
	public synchronized void mugituOntzia(String norabidea) {
		if (!Partida.getNirePartida().isJokoaHasiDa()) return;
		this.espaziontzia.mugitu(norabidea);
		talkakEgiaztatu();
		taulaEguneratu();
	}
	
	public synchronized void tiroEgin() {
		if (!Partida.getNirePartida().isJokoaHasiDa()) return;
		this.tiroak.addAll(this.espaziontzia.tiroEgin());
		taulaEguneratu();
	}
	
	private synchronized void eguneratuEtsaiak() {
		if (!Partida.getNirePartida().isJokoaHasiDa()) return;
		etsaiak.forEach(Entitatea::mugitu); // Java 8: forEach erabilita
		jokoEgoeraEgiaztatu();
		taulaEguneratu();
	}
	
	private synchronized void talkakEgiaztatu() {
		// Tiroak vs etsaiak (Biak Entitateak direnez, gainjartzenDira erabili dezakegu)
		for (int i = tiroak.size() - 1; i >= 0; i--) {
			Entitatea t = tiroak.get(i);
			boolean tiroakAsmatuDu = false;
			for (int j = etsaiak.size() - 1; j >= 0; j--) {
				Entitatea e = etsaiak.get(j);
				if (gainjartzenDira(t, e)) {
					etsaiak.remove(j); 
					tiroakAsmatuDu = true; 
					Partida.getNirePartida().gehituPuntuak(10);
					break; 
				}
			}
			if (tiroakAsmatuDu) { tiroak.remove(i); }
		}

		// Espaziontzia vs etsaiak (Java 8: anyMatch erabilita)
			if (etsaiak.stream().anyMatch(e -> gainjartzenDira(e, espaziontzia))) {
					Partida.getNirePartida().amaituJokoa(false); 
			}
		jokoEgoeraEgiaztatu();
	}
	
	private void jokoEgoeraEgiaztatu() {
	    if (!Partida.getNirePartida().isJokoaHasiDa()) return;
	    
	    if (this.etsaiak.isEmpty()) { 
	        Partida.getNirePartida().amaituJokoa(true);
	        return;
	    }
	    
	    // Java 8: anyMatch erabiliz inbasioa gertatzen den ikusteko
	    boolean inbasioa = etsaiak.stream().anyMatch(e -> e.getPixelek().stream().anyMatch(p -> e.getY() + p.getY() >= 59));

	    if (inbasioa) {
	        Partida.getNirePartida().amaituJokoa(false);
	    }
	}

	public boolean posizioaLibreDa(int x, int y, Entitatea mugitzenDenEtsaia) {
		// Etsaiaren pixel BATEK ERE EZ noneMatch-ekin talka egiten ez duela egiaztatu
		return mugitzenDenEtsaia.getPixelek().stream().noneMatch(p -> {
			int nx = x + p.getX();
			int ny = y + p.getY();
			
			// 1. Muga 
			boolean mugatikKanpo = (nx < 0 || nx >= 100 || ny >= 60);
			
			// 2. Beste etsaiak
			boolean talkaBesteEtsaiBatekin = etsaiak.stream().filter(e -> e != mugitzenDenEtsaia).anyMatch(e -> e.getPixelek().stream().anyMatch(ep -> nx == e.getX() + ep.getX() && ny == e.getY() + ep.getY()));
			
			return mugatikKanpo || talkaBesteEtsaiBatekin; 
		});
	}
	
	private synchronized void eguneratuTiroak() {
		tiroak.forEach(Entitatea::mugitu); // Java 8: forEach
		tiroak.removeIf(t -> t.getY() < 0); // Java 8: removeIf lambdarekin
		talkakEgiaztatu();
		taulaEguneratu();
	}
	
	//Espaziontzia eta etsaia elkar ukitzen duten begiratu
	private boolean gainjartzenDira(Entitatea e1, Entitatea e2) {
		for (Entitatea p1 : e1.getPixelek()) {
			int x1 = e1.getX() + p1.getX();
			int y1 = e1.getY() + p1.getY();
			for (Entitatea p2 : e2.getPixelek()) {
				int x2 = e2.getX() + p2.getX();
				int y2 = e2.getY() + p2.getY();
				if (x1 == x2 && y1 == y2) return true;
			}
		}
		return false;
	}
	
	public synchronized void aldatuArma() {
		this.espaziontzia.aldatuArma();
	}
}