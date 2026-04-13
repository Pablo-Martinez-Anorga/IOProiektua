package Eredua;

import java.util.ArrayList;
import java.util.List;

public class JokoKudeatzailea { 
	
	private static JokoKudeatzailea nireJK = null;
	
	private Espaziontzia espaziontzia;
	private List<Etsaia> etsaiak;
	private List<Tiroa> tiroak;
	private String ontziKolorea; 
	private Gelaxka[][] gelaxkak;
	
	private JokoKudeatzailea() {
		this.etsaiak = new ArrayList<>();
		this.tiroak = new ArrayList<>();
		// FACTORY PATROIA: Defektuzko ontzia sortu (Gero hasiJokoa-n aldatuko da)
		this.espaziontzia = EntitateFaktoria.getNireFaktoria().sortuEspaziontzia("GREEN", 50, 55);
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
		this.espaziontzia = EntitateFaktoria.getNireFaktoria().sortuEspaziontzia(this.ontziKolorea, 50, 55);
		
		etsaiakSortu(); 
		
		//WHILEAK KENDU HARIETAN
		Thread tiroenHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Partida.getNirePartida().isJokoaHasiDa()) {
					eguneratuTiroak(); 		
					try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
		tiroenHaria.start();
		
		
		Thread etsaienHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Partida.getNirePartida().isJokoaHasiDa()) {
					eguneratuEtsaiak(); 
					try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
		etsaienHaria.start();
		
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
	    for (Etsaia e : etsaiak) { markatuMatrizean(matrizVirtual, e); }
	    for (Tiroa t : tiroak) { markatuMatrizean(matrizVirtual, t); }

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
			for (Puntu p : e.getPixelek()) {
				int nx = ex + p.getDx();
				int ny = ey + p.getDy();
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
	        int x_posizioa = (int)(Math.random() * 93) + 3; 
	        boolean posizioaValida = false;
	        
	        while (!posizioaValida) {
	            posizioaValida = true;
	            for (int x_existente : erabilitakoX) {
	                if (Math.abs(x_existente - x_posizioa) < 5) {
	                    posizioaValida = false;
	                    x_posizioa = (int)(Math.random() * 93) + 3;
	                    break;
	                }
	            }
	        }
	        
	        erabilitakoX.add(x_posizioa);
	        this.etsaiak.add(EntitateFaktoria.getNireFaktoria().sortuEtsaia(x_posizioa, y_posizioa));
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
		for (Etsaia e : etsaiak) { e.mugitu(); }
		talkakEgiaztatu();
		jokoEgoeraEgiaztatu();
		taulaEguneratu();
	}
	
	private synchronized void talkakEgiaztatu() {
		// Tiroak vs etsaiak (Biak Entitateak direnez, gainjartzenDira erabili dezakegu)
		for (int i = tiroak.size() - 1; i >= 0; i--) {
			Tiroa t = tiroak.get(i);
			boolean tiroakAsmatuDu = false;
			for (int j = etsaiak.size() - 1; j >= 0; j--) {
				Etsaia e = etsaiak.get(j);
				if (gainjartzenDira(t, e)) {
					etsaiak.remove(j); 
					tiroakAsmatuDu = true; 
					break; 
				}
			}
			if (tiroakAsmatuDu) { tiroak.remove(i); }
		}

		// Espaziontzia vs etsaiak
		for (int i = 0; i < etsaiak.size(); i++) {
			if (gainjartzenDira(etsaiak.get(i), espaziontzia)) {
				Partida.getNirePartida().amaituJokoa(false); 
			}
		}
		jokoEgoeraEgiaztatu();
	}
	
	private void jokoEgoeraEgiaztatu() {
	    if (!Partida.getNirePartida().isJokoaHasiDa()) return;
	    
	    if (this.etsaiak.isEmpty()) { 
	        Partida.getNirePartida().amaituJokoa(true);
	        return;
	    }
	    
	    boolean inbasioa = false;
	    for (Etsaia e : etsaiak) {
	        for (Puntu p : e.getPixelek()) {
	            if (e.getY() + p.getDy() >= 59) {
	                inbasioa = true;
	                break;
	            }
	        }
	        if (inbasioa) break;
	    }

	    if (inbasioa) {
	        Partida.getNirePartida().amaituJokoa(false);
	    }
	}

	public boolean posizioaLibreDa(int x, int y, Entitatea mugitzenDenEtsaia) {
		for (Puntu p : mugitzenDenEtsaia.getPixelek()) {
			int nx = x + p.getDx();
			int ny = y + p.getDy();
			if (nx < 0 || nx >= 100 || ny >= 60) return false;
			
			for (Etsaia e : etsaiak) {
				if (e != mugitzenDenEtsaia) {
					for (Puntu ep : e.getPixelek()) {
						if (nx == e.getX() + ep.getDx() && ny == e.getY() + ep.getDy()) return false;
					}
				}
			}
		}
		return true;
	}
	
	private synchronized void eguneratuTiroak() {
		for (int i = 0; i < tiroak.size(); i++) {
			Tiroa t = tiroak.get(i);
			t.mugitu();
			if (t.getY() < 0) {
				tiroak.remove(i);
				i--; 
			}
		}
		talkakEgiaztatu();
		taulaEguneratu();
	}
	
	
	//Espaziontzia eta etsaia elkar ukitzen duten begiratu
	private boolean gainjartzenDira(Entitatea e1, Entitatea e2) {
		for (Puntu p1 : e1.getPixelek()) {
			int x1 = e1.getX() + p1.getDx();
			int y1 = e1.getY() + p1.getDy();
			for (Puntu p2 : e2.getPixelek()) {
				int x2 = e2.getX() + p2.getDx();
				int y2 = e2.getY() + p2.getDy();
				if (x1 == x2 && y1 == y2) return true;
			}
		}
		return false;
	}
	
	public synchronized void aldatuArma() {
		this.espaziontzia.aldatuArma();
	}

}