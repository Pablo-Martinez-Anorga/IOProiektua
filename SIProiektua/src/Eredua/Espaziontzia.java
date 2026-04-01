package Eredua;

import java.util.List;

public class Espaziontzia extends Entitatea {
	
	//Atributuak
	private TiroEstrategia[] armak;
	private int armaAktiboaIndex;
	
	//Eraikitzailea
	public Espaziontzia(int x, int y) { 
		super(x, y, 5, 3);
		//3 estrategiak kargatu
		this.armak = new TiroEstrategia[] {
				new TiroPixelEstrategia(),
				new TiroGeziEstrategia(),
				new TiroErronboEstrategia()
			};
		//Lehehengo estrategiarekin hasieratu
		this.armaAktiboaIndex = 0;
	}
	
	//Metodoak
	public void mugitu(String norabidea) {
		int zabaleraErdia = this.zabalera / 2;
		int altueraErdia = this.altuera / 2;
		if (norabidea.equals("Eskumara")) {
			if (this.x + zabaleraErdia < 99){
				this.x = this.x + 1;
			}
		}else if (norabidea.equals("Ezkerrera")) {
			if (this.x - zabaleraErdia > 0) {
				this.x = this.x - 1;
			}
		}else if (norabidea.equals("Gora")) {
			if (this.y - altueraErdia > 0) {
				this.y = this.y - 1;
			}
		}else if (norabidea.equals("Behera")) {
			if (this.y + altueraErdia < 59) {
				this.y = this.y + 1;
			}
		}
	}
	
	@Override
	public void mugitu() {
	}

	@Override
	public Egoera getEgoeraObject() {
	    return new EspaziontziaEgoera();
    }

	public void aldatuArma() {
		this.armaAktiboaIndex = (this.armaAktiboaIndex + 1) % this.armak.length;
	}
	
	public List<Tiroa> tiroEgin() {
		return this.armak[this.armaAktiboaIndex].tiroEgin(this.x, this.y, this.altuera);
	}
}