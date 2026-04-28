package Eredua;

import java.util.Observable;

public class Gelaxka extends Observable {
    
    private int x;
    private int y;
    
    private Egoera egoera; 

    public Gelaxka(int x, int y) {
        this.x = x;
        this.y = y;
        this.egoera = new HutsaEgoera(); //Hasierako egoera
    }
    
    //State patroiaren metodo estandar
    public void egoeraAldatu(Egoera egoeraBerria) {
    	if (egoeraBerria == null) {
            egoeraBerria = new HutsaEgoera();
        }
    	//Bakarrik abisatu egoera aldaltu denean
    	if (!this.egoera.getIzena().equals(egoeraBerria.getIzena())) {
    		this.egoera = egoeraBerria;
            setChanged();
            notifyObservers(this.egoera.getIzena()); // String-a bidali
    	}
    }

    public String getEgoera() { 
        // Egoera lortu
        return this.egoera.getIzena();
    }
    
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    
    
    public void hustu() { 
        egoeraAldatu(new HutsaEgoera());
    }
}