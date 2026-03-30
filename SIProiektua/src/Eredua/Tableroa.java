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
	
	// --- METODOA ZUZENDUTA ZABALERA ETA ALTUERAREKIN ---
	public void entitateaSartu(Entitatea e) {
		if (e != null) {
			int hasieraX = e.getX();
			int hasieraY = e.getY();
			int zabalera = e.getZabalera();
			int altuera = e.getAltuera();

			for (int i = hasieraX; i < hasieraX + zabalera; i++) {
				for (int j = hasieraY; j < hasieraY + altuera; j++) {
					if (i >= 0 && i < 100 && j >= 0 && j < 60) {
						// setEntitatea(e) ordez, setEgoera erabiltzen dugu bere motarekin
						this.gelaxkak[i][j].setEgoera(e.getMota());
					}
				}
			}
		}
	}
	
	// --- METODOA ZUZENDUTA ZABALERA ETA ALTUERAREKIN ---
	public void entitateaKendu(Entitatea e) {
		if (e != null) {
			int hasieraX = e.getX();
			int hasieraY = e.getY();
			int zabalera = e.getZabalera();
			int altuera = e.getAltuera();

			for (int i = hasieraX; i < hasieraX + zabalera; i++) {
				for (int j = hasieraY; j < hasieraY + altuera; j++) {
					if (i >= 0 && i < 100 && j >= 0 && j < 60) {
						this.gelaxkak[i][j].hustu();
					}
				}
			}
		}
	}
	
	public Gelaxka getGelaxka(int x, int y) {
		return this.gelaxkak[x][y];
	}
	
	public Gelaxka[][] getGelaxkak() {
		return this.gelaxkak;
	}

}