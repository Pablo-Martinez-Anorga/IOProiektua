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
		if (e != null) {
			int erdiaX = e.getX();
			int erdiaY = e.getY();
			int zabaleraErdia = e.getZabalera() / 2; 
			int altueraErdia = e.getAltuera() / 2;   

			for (int i = erdiaX - zabaleraErdia; i <= erdiaX + zabaleraErdia; i++) {
				for (int j = erdiaY - altueraErdia; j <= erdiaY + altueraErdia; j++) {
					if (i >= 0 && i < 100 && j >= 0 && j < 60) {
						this.gelaxkak[i][j].egoeraAldatu(e.getEgoeraObject());
					}
				}
			}
		}
	}
	// ----------------------------------
	
	private synchronized void taulaEguneratu() {
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
			
			// FACTORY PATROIA: Etsaia sortu
			this.etsaiak.add(EntitateFaktoria.getNireFaktoria().sortuEtsaia(x_posizioa, y_posizioa));
		}
		taulaEguneratu();
	}
	
	public synchronized void mugituOntzia(String norabidea) {
		this.espaziontzia.mugitu(norabidea);
		talkakEgiaztatu();
		taulaEguneratu();
	}
	
	public synchronized void tiroEgin() {
		this.tiroak.addAll(this.espaziontzia.tiroEgin());
		taulaEguneratu();
	}
	
	private synchronized void eguneratuEtsaiak() {
		for (Etsaia e : etsaiak) { e.mugitu(); }
		talkakEgiaztatu();
		jokoEgoeraEgiaztatu();
		taulaEguneratu();
	}
	
	private synchronized void talkakEgiaztatu() {
		//Tiroak vs etsaiak
		for (int i = tiroak.size() - 1; i >= 0; i--) {
			Tiroa t = tiroak.get(i);
			boolean tiroakAsmatuDu = false;
			for (int j = etsaiak.size() - 1; j >= 0; j--) {
				Etsaia e = etsaiak.get(j);
				if (barruanDago(t.getX(), t.getY(), e)) {
					etsaiak.remove(j); 
					tiroakAsmatuDu = true; 
					break; 
				}
			}
			if (tiroakAsmatuDu) { tiroak.remove(i); }
		}

		//Tiroak vs espaziontzia
		for (int i = 0; i < etsaiak.size(); i++) {
			Etsaia e = etsaiak.get(i);
			if (gainjartzenDira(e, espaziontzia)) {
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
		int i = 0;
		while (i < etsaiak.size() && !inbasioa) {
			if (etsaiak.get(i).getY() >= 59) { 
				Partida.getNirePartida().amaituJokoa(false); 
				inbasioa = true;
			}
			i++;
		}
	}

	public boolean posizioaLibreDa(int x, int y, Entitatea mugitzenDenEtsaia) {
		int eMinX = x - (mugitzenDenEtsaia.getZabalera() / 2);
		int eMaxX = x + (mugitzenDenEtsaia.getZabalera() / 2);
		int eMinY = y - (mugitzenDenEtsaia.getAltuera() / 2);
		int eMaxY = y + (mugitzenDenEtsaia.getAltuera() / 2);

		if (eMinX < 0 || eMaxX >= 100 || eMaxY >= 60) return false;
		for (Etsaia e : etsaiak) {
			if (e != mugitzenDenEtsaia) {
				int bMinX = e.getX() - (e.getZabalera() / 2);
				int bMaxX = e.getX() + (e.getZabalera() / 2);
				int bMinY = e.getY() - (e.getAltuera() / 2);
				int bMaxY = e.getY() + (e.getAltuera() / 2);

				if (eMinX <= bMaxX && eMaxX >= bMinX && eMinY <= bMaxY && eMaxY >= bMinY) {
					return false; 
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
	
	//Tiroa entitate baten barruan dagoen begiratu
	private boolean barruanDago(int px, int py, Entitatea e) {
		int eMinX = e.getX() - (e.getZabalera() / 2);
		int eMaxX = e.getX() + (e.getZabalera() / 2);
		int eMinY = e.getY() - (e.getAltuera() / 2);
		int eMaxY = e.getY() + (e.getAltuera() / 2);
		return (px >= eMinX && px <= eMaxX && py >= eMinY && py <= eMaxY);
	}

	//Espaziontzia eta etsaia elkar ukitzen duten begiratu
	private boolean gainjartzenDira(Entitatea e1, Entitatea e2) {
		int e1MinX = e1.getX() - (e1.getZabalera() / 2);
		int e1MaxX = e1.getX() + (e1.getZabalera() / 2);
		int e1MinY = e1.getY() - (e1.getAltuera() / 2);
		int e1MaxY = e1.getY() + (e1.getAltuera() / 2);

		int e2MinX = e2.getX() - (e2.getZabalera() / 2);
		int e2MaxX = e2.getX() + (e2.getZabalera() / 2);
		int e2MinY = e2.getY() - (e2.getAltuera() / 2); 
		int e2MaxY = e2.getY() + (e2.getAltuera() / 2);

		return (e1MinX <= e2MaxX && e1MaxX >= e2MinX && e1MinY <= e2MaxY && e1MaxY >= e2MinY);
	}
	
	public synchronized void aldatuArma() {
		this.espaziontzia.aldatuArma();
	}

}