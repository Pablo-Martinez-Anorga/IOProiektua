package Eredua;

import java.util.ArrayList;
import java.util.List;

public class JokoKudeatzailea {
	
	// Singleton patroia
	private static JokoKudeatzailea nireJK = null;
	
	private Espaziontzia espaziontzia;
	private List<Etsaia> etsaiak;
	private List<Tiroa> tiroak;
	private Tableroa nireTableroa;
	private String ontziKolorea; 
	
	private JokoKudeatzailea() {
		this.nireTableroa = new Tableroa();
		this.etsaiak = new ArrayList<>();
		this.tiroak = new ArrayList<>();
		this.espaziontzia = new Espaziontzia(50, 55); 
	}
	
	public static JokoKudeatzailea getNireJK() {
		if (nireJK == null) {
			nireJK = new JokoKudeatzailea();
		}
		return nireJK;
	}
	
	public void setOntziKolorea(String pKolorea) {
		this.ontziKolorea = pKolorea;
	}

	public String getOntziKolorea() {
		return this.ontziKolorea;
	}

	public Tableroa getTableroa() {
		return this.nireTableroa;
	}
	
	// Hariak Partidaren egoeraren arabera mugitu
	public void hasiJokoa() {
		etsaiakSortu(); 
		
		Thread tiroenHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Partida.getNirePartida().isJokoaHasiDa()) {
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
		
		Thread etsaienHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Partida.getNirePartida().isJokoaHasiDa()) {
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
		this.nireTableroa.garbituMatrizea();
		this.nireTableroa.entitateaSartu(this.espaziontzia);
		for (Etsaia e : etsaiak) {
			this.nireTableroa.entitateaSartu(e);
		}	
		for (Tiroa t : tiroak) {
			this.nireTableroa.entitateaSartu(t);
		}
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

		// Galdu dugun egiaztatu
		for (int i = 0; i < etsaiak.size(); i++) {
			Etsaia e = etsaiak.get(i);
			if (e.getX() == espaziontzia.getX() && e.getY() == espaziontzia.getY()) {
				Partida.getNirePartida().amaituJokoa(false); 
			}
		}
		jokoEgoeraEgiaztatu();
	}
	
	public void jokoEgoeraEgiaztatu() {
		if (etsaiak.isEmpty() && Partida.getNirePartida().isJokoaHasiDa()) {
			Partida.getNirePartida().amaituJokoa(true); 
		}
		
		if (Partida.getNirePartida().isJokoaHasiDa()) {
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
	}

	public boolean posizioaLibreDa(int x, int y) {
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