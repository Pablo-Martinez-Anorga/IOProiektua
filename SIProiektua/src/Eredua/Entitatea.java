package Eredua;

import java.util.ArrayList;
import java.util.List;

public abstract class Entitatea {
	protected int x; // Nodoan badago, dx (offset-a) izango da; JokoKudeatzailean badago, X absolutua.
	protected int y;
	
	public Entitatea(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	// COMPOSITE GAKOA: Monopixel batek bere burua itzultzen du zerrenda batean.
	public List<Entitatea> getPixelek() {
		List<Entitatea> pixelak = new ArrayList<>();
		pixelak.add(this);
		return pixelak;
	}
	
	public abstract void mugitu();
	public abstract Egoera getEgoeraObject();
}