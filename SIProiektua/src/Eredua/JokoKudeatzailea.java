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
	//
	
	public int[][] getMatrizea(){
		return this.matrizea;
	}
	
	public void hasiJokoa() {
		this.jokoaHasiDa = true;
		
		setChanged();
        notifyObservers();
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
        // Etsaiak beherantz mugitzeko
		for (Etsaia e : etsaiak) {
            e.mugitu();
            
        }
		 setChanged();
	        notifyObservers();
    }
	
	public void eguneratuTiroak() {
        // Tiroak gorantz mugitzeko
		for (Tiroa t : tiroak) {
            t.mugitu();
        }
        setChanged();
        notifyObservers();
    }
	
	public void talkakEgiaztatu() {
        // Tiroek etsaiak jotzen dituzten begiratzeko
		setChanged();
        notifyObservers();
    }

}
