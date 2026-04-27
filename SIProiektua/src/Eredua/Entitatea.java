package Eredua;

import java.util.ArrayList;
import java.util.List;

public abstract class Entitatea {
	protected int x;
	protected int y;
	protected IrudiKonposatua irudia; // HEMEN SARTZEN DA COMPOSITE-A
	
	public Entitatea(int x, int y) {
		this.x = x;
		this.y = y;
		this.irudia = new IrudiKonposatua();
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public IrudiKonposatua getIrudia() { return this.irudia; }
	
	// Metodo lagungarria zuzenean pixelak eskatzeko
	public List<Puntu> getPixelek() {
		List<Puntu> pixelGuztiak = new ArrayList<>();
		this.irudia.betePixelekin(pixelGuztiak);
		return pixelGuztiak;
	}
	
	public abstract void mugitu(); 
	public abstract Egoera getEgoeraObject();
}