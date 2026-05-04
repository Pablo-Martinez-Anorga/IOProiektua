package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class JokoKudeatzailea { 
	
	private static JokoKudeatzailea nireJK = null;
	
	private Entitatea espaziontzia;
	private List<Entitatea> etsaiak = new ArrayList<>();
	private List<Entitatea> tiroak = new ArrayList<>();
	private String ontziKolorea; 
	private Gelaxka[][] gelaxkak;
	
	private JokoKudeatzailea() {
		this.etsaiak = new ArrayList<>();
		this.tiroak = new ArrayList<>();
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
                matrizVirtual[i][j] = new GelaxkaHutsa(); 
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
            if (e instanceof EtsaiNodo) {
                for (Entitatea p : ((EtsaiNodo) e).getPixelek()) {
                    pintatuMatrizan(matrizVirtual, p.getX(), p.getY(), new GelaxkaEtsai());
                }
            } else if (e instanceof EspaziontziNodo) {
                for (Entitatea p : ((EspaziontziNodo) e).getPixelek()) {
                    pintatuMatrizan(matrizVirtual, p.getX(), p.getY(), new GelaxkaEspaziontzi());
                }
            } else if (e instanceof TiroNodo) {
                for (Entitatea p : ((TiroNodo) e).getPixelek()) {
                    pintatuMatrizan(matrizVirtual, p.getX(), p.getY(), new GelaxkaTiro());
                }
            }
        }
    }
	
	private void pintatuMatrizan(Egoera[][] matriz, int x, int y, Egoera egoera) {
        if (x >= 0 && x < 100 && y >= 0 && y < 60) {
            matriz[x][y] = egoera;
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
		if (this.espaziontzia instanceof EspaziontziNodo) {
			EspaziontziNodo nodo = (EspaziontziNodo) this.espaziontzia;
			if (nodo.mugituDaiteke(norabidea)) {
				nodo.mugitu(norabidea);
			}
		}
		talkakEgiaztatu();
		taulaEguneratu();
	}
	
	public synchronized void tiroEgin() {
		if (!Partida.getNirePartida().isJokoaHasiDa()) return;
		if (this.espaziontzia instanceof EspaziontziNodo) {
			List<Entitatea> sortutakoTiroak = ((EspaziontziNodo) this.espaziontzia).tiroEgin();
			this.tiroak.addAll(sortutakoTiroak);
			System.out.println("Tiroak sortu dira. Orain " + this.tiroak.size() + " tiro daude zerrendan."); // 2. Konprobazioa
		}
		taulaEguneratu();
	}
	
	private synchronized void eguneratuEtsaiak() {
		if (!Partida.getNirePartida().isJokoaHasiDa()) return;
		for (Entitatea e : etsaiak) {
			e.mugitu();
		}
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
	    
	    boolean inbasioa = etsaiak.stream().anyMatch(e -> {
	    	if (e instanceof EtsaiNodo) {
	    		return ((EtsaiNodo) e).getPixelek().stream().anyMatch(p -> p.getY() >= 59);
	    	}
	    	return false;
	    });

	    if (inbasioa) Partida.getNirePartida().amaituJokoa(false);
	}

	public boolean posizioaLibreDa(int x, int y, Entitatea mugitzenDenEtsaia) {
		if (!(mugitzenDenEtsaia instanceof EtsaiNodo)) return false;
		EtsaiNodo etsaiNodoa = (EtsaiNodo) mugitzenDenEtsaia;
		
		return etsaiNodoa.getPixelek().stream().noneMatch(p -> {
            int offsetX = p.getX() - etsaiNodoa.getX();
            int offsetY = p.getY() - etsaiNodoa.getY();
			int nx = x + offsetX;
			int ny = y + offsetY;
			
			boolean mugatikKanpo = (nx < 0 || nx >= 100 || ny >= 60);
			boolean talkaBesteEtsaiBatekin = etsaiak.stream()
				.filter(e -> e != mugitzenDenEtsaia)
				.anyMatch(e -> {
					if (e instanceof EtsaiNodo) {
						return ((EtsaiNodo) e).getPixelek().stream()
							.anyMatch(ep -> nx == ep.getX() && ny == ep.getY());
					}
					return false;
				});
			return mugatikKanpo || talkaBesteEtsaiBatekin; 
		});
	}
	
	private synchronized void eguneratuTiroak() {
		for (Entitatea t : tiroak) {
			t.mugitu("Gora"); // Jokalariaren tiroa bada gora, edo logika sartu
		}
		tiroak.removeIf(t -> t.getY() < 0); 
		talkakEgiaztatu();
		taulaEguneratu();
	}
	
	private List<Entitatea> lortuPixelak(Entitatea e) {
		if (e instanceof EspaziontziNodo) return ((EspaziontziNodo) e).getPixelek();
		if (e instanceof EtsaiNodo) return ((EtsaiNodo) e).getPixelek();
		if (e instanceof TiroNodo) return ((TiroNodo) e).getPixelek();
		return new ArrayList<>();
	}
	
	//Espaziontzia eta etsaia elkar ukitzen duten begiratu
	private boolean gainjartzenDira(Entitatea e1, Entitatea e2) {
		List<Entitatea> pixelek1 = lortuPixelak(e1);
		List<Entitatea> pixelek2 = lortuPixelak(e2);
		
		for (Entitatea p1 : pixelek1) {
			int x1 = p1.getX();
			int y1 = p1.getY();
			for (Entitatea p2 : pixelek2) {
				int x2 = p2.getX();
				int y2 = p2.getY();
				if (x1 == x2 && y1 == y2) return true;
			}
		}
		return false;
	}
	
	public synchronized void aldatuArma() {
		if (this.espaziontzia instanceof EspaziontziNodo) {
            ((EspaziontziNodo) this.espaziontzia).aldatuArma();
        }
	}
	
	public boolean gelaxkaEtsaiaDa(int x, int y) {
		for (Entitatea e : this.etsaiak) {
            if (e instanceof EtsaiNodo) {
            	EtsaiNodo nodo = (EtsaiNodo) e;
                for (Entitatea p : nodo.getPixelek()) {
                	if (p.getX() == x && p.getY() == y) return true;
                }
            }
        }
        return false;
	}
	
	public int getEtsaiaId(int x, int y) {
		for (Entitatea e : this.etsaiak) {
            if (e instanceof EtsaiNodo) {
            	EtsaiNodo nodo = (EtsaiNodo) e;
                for (Entitatea p : nodo.getPixelek()) {
                    if (p.getX() == x && p.getY() == y) return ((Etsaia) p).getId(); 
                }
            }
        }
        return -1;
    }
}