package Eredua;

public class Tableroa {
	
	// Atributuak
	private Gelaxka[][] gelaxkak;
	
	// Eraikitzailea
	public Tableroa() {
		this.gelaxkak = new Gelaxka[100][60];
		hasieratuGelaxkak();
	}
	
	// Metodoak
	private void hasieratuGelaxkak() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 60; j++) {
				this.gelaxkak[i][j] = new Gelaxka(i, j);
			}
		}
	}
	
	public void garbituMatrizea() {
		// Gelaxka guztiak hustu
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 60; j++) {
				this.gelaxkak[i][j].hustu();
			}
		}
	}
	
	public void entitateaSartu(Entitatea e) {
		if (e != null && e.getX() >= 0 && e.getX() < 100 && e.getY() >= 0 && e.getY() < 60) {
			this.gelaxkak[e.getX()][e.getY()].setEntitatea(e);
		}
	}
	
	public void entitateaKendu(Entitatea e) {
		if (e != null && e.getX() >= 0 && e.getX() < 100 && e.getY() >= 0 && e.getY() < 60) {
			this.gelaxkak[e.getX()][e.getY()].hustu();
		}
	}
	
	public Gelaxka getGelaxka(int x, int y) {
		return this.gelaxkak[x][y];
	}
	
	public Gelaxka[][] getGelaxkak() {
		return this.gelaxkak;
	}

}