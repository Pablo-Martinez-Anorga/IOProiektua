package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroErronboEstrategia implements TiroEstrategia{
	
	@Override
	public List<Tiroa> tiroEgin(int ontziX, int ontziY, int ontziAltuera) {
		List<Tiroa> berriak = new ArrayList<>();
		berriak.add(EntitateFaktoria.getNireFaktoria().sortuTiroa(ontziX, ontziY - 2, "ERRONBO"));
		return berriak;
	}

}
