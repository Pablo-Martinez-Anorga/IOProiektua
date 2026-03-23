package Eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
    
    private int x;
    private int y;
    private String egoera; 

    public Gelaxka(int x, int y) {
        this.x = x;
        this.y = y;
        this.egoera = "HUTSA"; // hutsarekin hasieratu?
    }
    
    //GELAXKA BAKOITZARI EGOERA BAT JARRI, KOORDENATUZ LOTU ENTITATEAREKIN, ENTITATEA ATRIBUTUA KENDU

    public void setEgoera(String egoeraBerria) {
        this.egoera = egoeraBerria;
        setChanged();
        notifyObservers(this.egoera);
    }

    public String getEgoera() { 
    	return this.egoera;
    }
    
    public int getX() { 
    	return this.x; 
    }
    
    public int getY() { 
    	return this.y; 
    	}
    
    public boolean isHutsik() { 
    	return this.egoera.equals("HUTSA"); 
    }
    
    public void hustu() { 
    	setEgoera("HUTSA");
    }
}