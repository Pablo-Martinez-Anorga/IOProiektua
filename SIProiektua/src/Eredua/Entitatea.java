package Eredua;

public abstract class Entitatea {
	
	//Atributuak
	protected int x;
	protected int y;
	protected int zabalera; 
	protected int altuera;  
	
	//Eraikitzailea 
	public Entitatea(int x, int y, int zabalera, int altuera) {
		this.x = x;
		this.y = y;
		this.zabalera = zabalera;
		this.altuera = altuera;
	}
	
	//Metodoak
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getZabalera() { return this.zabalera; } 
	public int getAltuera() { return this.altuera; }   
	
	public abstract void mugitu(); 
	public abstract String getMota();
}