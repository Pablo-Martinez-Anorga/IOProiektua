package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroGeziEstrategia implements TiroEstrategia {

	@Override
	public List<Entitatea> tiroEgin(int ontziX, int ontziY, int ontziAltuera) {
		List<Entitatea> berriak = new ArrayList<>();
		// Faktoriari eskatzen diogu "GEZI" bat sortzeko
		berriak.add(TiroFaktoria.getNireFaktoria().sortuTiroa(ontziX, ontziY - 2, "GEZI"));
		return berriak;
	}
}
