package Eredua;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class JokoKudeatzailea extends Observable{
	
	//Atributu estatikoa
	private static JokoKudeatzailea nireJK = null;
	
	//Atributuak
	private int[][] matrizea;
	private boolean jokoaHasiDa;
	private Espaziontzia espaziontzia;
    private List<Etsaia> etsaiak;
    private List<Tiroa> tiroak;
	
	//Eraikitzailea
	private JokoKudeatzailea() {
		this.matrizea = new int [100][60];
		this.jokoaHasiDa = false;
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
	
	//Metodoak (setChanged(); eta notifyObservers(); metodo guztietan)
	
	public int[][] getMatrizea(){
		//Matrizea garbitu
		this.matrizea = new int[100][60];
		//Ontzia sartu
		if (this.espaziontzia != null) {
			this.matrizea[this.espaziontzia.getX()][this.espaziontzia.getY()] = 1;
		}
		//Etsaiak sartu
		for (Etsaia e : etsaiak) {
			if (e.getX() >= 0 && e.getX() < 100 && e.getY() >= 0 && e.getY() < 60) {
				this.matrizea[e.getX()][e.getY()] = 2;
			}
		}
		//Tiroak sartu
		for (Tiroa t : tiroak) {
			if (t.getX() >= 0 && t.getX() < 100 && t.getY() >= 0 && t.getY() < 60) {
				this.matrizea[t.getX()][t.getY()] = 3;
			}
		}
		
		return this.matrizea;
	}
	
	public void hasiJokoa() {
		this.jokoaHasiDa = true;
		//Etsaiak sortu
		etsaiakSortu();
		//Tiroak mugitzeko
		Thread tiroenHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (jokoaHasiDa) {
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
		//Etsaiek mugitu
		Thread etsaienHaria = new Thread(new Runnable() {
			@Override
			public void run() {
				while (jokoaHasiDa) {
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
		setChanged();
        notifyObservers();
	}
	
	private void etsaiakSortu() {
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
			int y_posizioa = 5;           
			
			Etsaia etsaiBerria = new Etsaia(x_posizioa, y_posizioa);
			this.etsaiak.add(etsaiBerria);
		}
	}

	public void mugituOntzia(String norabidea) {
		this.espaziontzia.mugitu(norabidea);
        setChanged();
        notifyObservers();
    }
	
	public void tiroEgin() {
		Tiroa berria = new Tiroa(this.espaziontzia.getX(), this.espaziontzia.getY() - 1);
        this.tiroak.add(berria);
        setChanged();
        notifyObservers();
    }
	
	public void eguneratuEtsaiak() {
        //Etsaiak mugitzeko
		for (int i = 0; i < etsaiak.size(); i++) {
			etsaiak.get(i).mugitu();
		}
		setChanged();
		notifyObservers();
    }
	
	public void eguneratuTiroak() {
        //Tiroak gorantz mugitzeko
		for (int i = 0; i < tiroak.size(); i++) {
			Tiroa t = tiroak.get(i);
			t.mugitu();
			//Pantailatik ateratzen bada, zerrendatik ezabatu
			if (t.getY() < 0) {
				tiroak.remove(i);
				i--; //Indizea atzera bota, bat ezabatu dugulako
			}
		}
        setChanged();
        notifyObservers();
    }
	
	public void talkakEgiaztatu() {
        // Tiroek etsaiak jotzen dituzten begiratzeko
		setChanged();
        notifyObservers();
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
