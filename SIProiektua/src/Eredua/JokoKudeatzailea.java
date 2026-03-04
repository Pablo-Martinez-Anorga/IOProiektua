package Eredua;

import java.util.ArrayList;
import java.util.List;

public class JokoKudeatzailea{
	
	//Atributu estatikoa
	private static JokoKudeatzailea nireJK = null;
	
	//Atributuak
	private Espaziontzia espaziontzia;
    private List<Etsaia> etsaiak;
    private List<Tiroa> tiroak;
    private Tableroa nireTableroa;
	private Partida unekoPartida;
	
	//Eraikitzailea
	private JokoKudeatzailea() {
		this.nireTableroa = new Tableroa();
		this.unekoPartida = new Partida();
		this.etsaiak = new ArrayList<>();
        this.tiroak = new ArrayList<>();
        this.espaziontzia = new Espaziontzia(50, 55);//Hasierako posizioa
	}
	
	//get metodoa Singleton
	public static JokoKudeatzailea getNireJK() {
		if (nireJK == null) {
			nireJK = new JokoKudeatzailea();
		}
		return nireJK;
	}
	
	//Metodoak
	
	public Tableroa getTableroa() {
		return this.nireTableroa;
	}
	
	private void taulaEguneratu() {
		nireTableroa.garbituMatrizea();
		nireTableroa.entitateaSartu(espaziontzia); //Ontzia jarri
		for (Etsaia e : etsaiak) {
			nireTableroa.entitateaSartu(e); //Etsaiak jarri
		}
		for (Tiroa t : tiroak) {
			nireTableroa.entitateaSartu(t); //Tiroak jarri
		}
		nireTableroa.bistaEguneratu();
	}
	
	public void hasiJokoa() {
		unekoPartida.hasiJokoa();
		//Etsaiak sortu
		etsaiakSortu();
		//Tiroak mugitzeko
		Thread tiroenHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (unekoPartida.isJokoaHasiDa()) {
					eguneratuTiroak(); //Tiroak igo		
					try {
						Thread.sleep(50); //Tiroan abiadura
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		tiroenHaria.start();
		//Etsaiak mugitu
		Thread etsaienHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (unekoPartida.isJokoaHasiDa()) {
					eguneratuEtsaiak(); 
					try {
						Thread.sleep(200); //Etsaien abiadura
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		etsaienHaria.start();
		taulaEguneratu();
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
			Etsaia etsaiBerria = new Etsaia(x_posizioa, y_posizioa);
			this.etsaiak.add(etsaiBerria);
		}
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
        //Etsaiak mugitzeko
		for (int i = 0; i < etsaiak.size(); i++) {
			etsaiak.get(i).mugitu();
		}
		taulaEguneratu();
    }
	
	public void eguneratuTiroak() {
        //Tiroak gorantz mugitzeko
		for (int i = 0; i < tiroak.size(); i++) {
			Tiroa t = tiroak.get(i);
			t.mugitu();
			//Pantailatik ateratzen bada, zerrendatik ezabatu
			if (t.getY() < 0) {
				tiroak.remove(i);//Ezabatu pantailatik kanpo
				i--; //Indizea atzera bota, bat ezabatu dugulako
			}
		}
		taulaEguneratu();
    }
	
	public void talkakEgiaztatu() {
        // Tiroek etsaiak jotzen dituzten begiratzeko
		//TODO
		taulaEguneratu();
    }
	
	public boolean posizioaLibreDa(int pX, int pY) {
	    for (Etsaia e : etsaiak) {
	        if (e.getX() == pX && e.getY() == pY) {
	            return false;
	        }
	    }
	    return true;
	}

}
