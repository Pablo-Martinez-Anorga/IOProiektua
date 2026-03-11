package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable; 

public class JokoKudeatzailea extends Observable { 
	
	private static JokoKudeatzailea nireJK = null;
	
	private Espaziontzia espaziontzia;
    private List<Etsaia> etsaiak;
    private List<Tiroa> tiroak;
	private Partida unekoPartida;
	private String ontziKolorea; 
	private Gelaxka[][] gelaxkak;
	
	private JokoKudeatzailea() {
		this.unekoPartida = new Partida();
		this.etsaiak = new ArrayList<>();
        this.tiroak = new ArrayList<>();
        this.espaziontzia = new Espaziontzia(50, 55);
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
	
	public Partida getUnekoPartida() {
		return this.unekoPartida;
	}
	
	public void setOntziKolorea(String pKolorea) { this.ontziKolorea = pKolorea; }
	public String getOntziKolorea() { return this.ontziKolorea; }

	public Gelaxka getGelaxka(int x, int y) {
		return this.gelaxkak[x][y];
	}
	
	public void hasiJokoa() {
		this.unekoPartida.hasiJokoa();
		etsaiakSortu(); 
		
		setChanged();
		notifyObservers("HASI");
		
		Thread tiroenHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (unekoPartida.isJokoaHasiDa()) {
					eguneratuTiroak(); 		
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
		tiroenHaria.start();
		
		Thread etsaienHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (unekoPartida.isJokoaHasiDa()) {
					eguneratuEtsaiak(); 
					try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
		etsaienHaria.start();
		
		taulaEguneratu();
	}
	
	private void garbituMatrizea() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 60; j++) {
				if (!this.gelaxkak[i][j].isHutsik()) { 
					this.gelaxkak[i][j].hustu();
				}
			}
		}
	}
	
	private void entitateaSartu(Entitatea e) {
		if (e != null && e.getX() >= 0 && e.getX() < 100 && e.getY() >= 0 && e.getY() < 60) {
			this.gelaxkak[e.getX()][e.getY()].setEntitatea(e);
		}
	}
	
	private void taulaEguneratu() {
		garbituMatrizea();
		entitateaSartu(this.espaziontzia);
		for (Etsaia e : etsaiak) { entitateaSartu(e); }	
		for (Tiroa t : tiroak) { entitateaSartu(t); }
	}	

	private void etsaiakSortu() {
		int y_posizioa = 5;
		this.etsaiak.clear();
		int etsaiKopurua = (int)(Math.random() * 5) + 4;
		ArrayList<Integer> erabilitakoX = new ArrayList<>();
		for (int i = 0; i < etsaiKopurua; i++) {
			int x_posizioa = (int)(Math.random() * 100); 
			while (erabilitakoX.contains(x_posizioa)) { x_posizioa = (int)(Math.random() * 100); }       
			erabilitakoX.add(x_posizioa);
			this.etsaiak.add(new Etsaia(x_posizioa, y_posizioa));
		}
		taulaEguneratu();
	}

	public void mugituOntzia(String norabidea) {
		this.espaziontzia.mugitu(norabidea);
		talkakEgiaztatu();
		taulaEguneratu();
    }
	
	public void tiroEgin() {
		this.tiroak.add(new Tiroa(this.espaziontzia.getX(), this.espaziontzia.getY() - 2));
        taulaEguneratu();
    }
	
	public void eguneratuEtsaiak() {
		for (Etsaia e : etsaiak) { e.mugitu(); }
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
	        if (tiroakAsmatuDu) { tiroak.remove(i); }
	    }

	    for (int i = 0; i < etsaiak.size(); i++) {
	        Etsaia e = etsaiak.get(i);
	        if (e.getX() == espaziontzia.getX() && e.getY() == espaziontzia.getY()) {
	            unekoPartida.amaituJokoa(false); 
	        }
	    }
	    jokoEgoeraEgiaztatu();
    }
	
	public void jokoEgoeraEgiaztatu() {
		if (!unekoPartida.isJokoaHasiDa()) return;
		
		unekoPartida.etsaiKopuruaEguneratu(etsaiak.size());
	    
	    boolean inbasioa = false;
	    int i = 0;
	    while (i < etsaiak.size() && !inbasioa) {
	        if (etsaiak.get(i).getY() >= 59) { 
	            unekoPartida.amaituJokoa(false); 
	            inbasioa = true;
	        }
	        i++;
	    }
	}

	public boolean posizioaLibreDa(int x, int y) {
		for (Etsaia e : etsaiak) {
			if (e.getX() == x && e.getY() == y) {
				return false;
			}
		}
		return this.gelaxkak[x][y].isHutsik();
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