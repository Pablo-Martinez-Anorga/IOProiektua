package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroErronboEstrategia implements TiroEstrategia {
	
	@Override
	public List<Entitatea> tiroEgin(int ontziX, int ontziY, int ontziAltuera) {
		List<Entitatea> berriak = new ArrayList<>();
		// Faktoriari eskatzen diogu "ERRONBO" bat sortzeko
		berriak.add(TiroFaktoria.getNireFaktoria().sortuTiroa(ontziX, ontziY - 2, "ERRONBO"));
		return berriak;
	}
}