package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class JokoKudeatzailea extends Observable {
	
	// Atributu estatikoa (Singleton)
	private static JokoKudeatzailea nireJK = null;
	
	// Atributuak
	private Espaziontzia espaziontzia;
    private List<Etsaia> etsaiak;
    private List<Tiroa> tiroak;
    private Tableroa nireTableroa;
	private Partida unekoPartida;
	private String ontziKolorea; // Orain String da, ez Color (Bistak erabakiko du)
	
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
	
	// Kolorea kudeatzeko metodoak (String bezala)
	public void setOntziKolorea(String pKolorea) {
	    this.ontziKolorea = pKolorea;
	}

	public String getOntziKolorea() {
	    return this.ontziKolorea;
	}

	public Tableroa getTableroa() {
		return this.nireTableroa;
	}
	
	public void hasiJokoa() {
		this.unekoPartida.hasiJokoa();
		etsaiakSortu(); 
		
		// Tiroen haria
		Thread tiroenHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (unekoPartida.isJokoaHasiDa()) {
					eguneratuTiroak(); 		
					try {
						Thread.sleep(50); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		tiroenHaria.start();
		
		// Etsaien haria
		Thread etsaienHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (unekoPartida.isJokoaHasiDa()) {
					eguneratuEtsaiak(); 
					try {
						Thread.sleep(200); 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		etsaienHaria.start();
		
		taulaEguneratu();
	}
	
	private void taulaEguneratu() {
		// Matrizea garbitu
		this.nireTableroa.garbituMatrizea();
		// Entitateak sartu
		this.nireTableroa.entitateaSartu(this.espaziontzia);
		for (Etsaia e : etsaiak) {
			this.nireTableroa.entitateaSartu(e);
		}	
		for (Tiroa t : tiroak) {
			this.nireTableroa.entitateaSartu(t);
		}
		// Oharra: Gelaxkak automatikoki eguneratzen dira orain, ez dugu bistaEguneratu deitu behar
	}	

	private void etsaiakSortu() {
		int y_posizioa = 5;
		this.etsaiak.clear();
		int etsaiKopurua = (int)(Math.random() * 5) + 4;
		ArrayList<Integer> erabilitakoX = new ArrayList<>();
		for (int i = 0; i < etsaiKopurua; i++) {
			int x_posizioa = (int)(Math.random() * 100); 
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
		talkakEgiaztatu();
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
		talkakEgiaztatu();
		jokoEgoeraEgiaztatu();
		taulaEguneratu();
    }
	
	public void talkakEgiaztatu() {
		for (int i = tiroak.size() - 1; i >= 0; i--) {
	        Tiroa t = tiroak.get(i);
	        boolean tiroakAsmatuDu = false;
	        for (int j = etsaiak.size() - 1; j >= 0; j--) {
	            Etsaia e = etsaiak.get(j);
	            if (t.getX() == e.getX() && Math.abs(t.getY() - e.getY()) <= 1) {
	                etsaiak.remove(j); 
	                tiroakAsmatuDu = true; 
	                break; 
	            }
	        }
	        if (tiroakAsmatuDu) {
	            tiroak.remove(i);
	        }
	    }

	    for (int i = 0; i < etsaiak.size(); i++) {
	        Etsaia e = etsaiak.get(i);
	        if (e.getX() == espaziontzia.getX() && e.getY() == espaziontzia.getY()) {
	            unekoPartida.amaituJokoa(false); 
	            setChanged();
	            notifyObservers("GALDU"); // Bistari abisatu
	        }
	    }
	    jokoEgoeraEgiaztatu();
    }
	
	public void jokoEgoeraEgiaztatu() {
	    if (etsaiak.isEmpty() && unekoPartida.isJokoaHasiDa()) {
	        unekoPartida.amaituJokoa(true); 
	        setChanged();
	        notifyObservers("IRABAZI"); // Bistari abisatu
	    }
	    
	    if (unekoPartida.isJokoaHasiDa()) {
	        boolean inbasioa = false;
	        int i = 0;
	        while (i < etsaiak.size() && !inbasioa) {
	            if (etsaiak.get(i).getY() >= 59) { 
	                unekoPartida.amaituJokoa(false); 
	                inbasioa = true;
	                setChanged();
	                notifyObservers("GALDU"); // Bistari abisatu
	            }
	            i++;
	        }
	    }
	}

	public boolean posizioaLibreDa(int x, int y) {
		// Orain Gelaxka erabiltzen dugu
		return this.nireTableroa.getGelaxka(x, y).isHutsik();
	}
	
	public void eguneratuTiroak() {
		for (int i = 0; i < tiroak.size(); i++) {
			Tiroa t = tiroak.get(i);
			talkakEgiaztatu();
			t.mugitu();
			if (t.getY() < 0) {
				tiroak.remove(i);
				i--; 
			}
		}
		taulaEguneratu();
	}

}
