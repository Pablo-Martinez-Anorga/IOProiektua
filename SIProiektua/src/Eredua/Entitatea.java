package Eredua;

public abstract class Entitatea {
	
	//Atributuak
	protected int x;
	protected int y;
	
	//Eraikitzailea
	public Entitatea(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Metodoak
	public int getX() {
		return this.x;
	}

	
	public int getY() {
		return this.y;
	}
	
	public abstract void mugitu(); 
	
	public abstract String getMota();
}
