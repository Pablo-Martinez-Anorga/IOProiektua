package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroPixelEstrategia implements TiroEstrategia{

	@Override
	public List<Tiroa> tiroEgin(int ontziX, int ontziY, int ontziAltuera) {
		List<Tiroa> berriak = new ArrayList<>();
		// Tiro básico: 1 solo píxel
		berriak.add(EntitateFaktoria.getNireFaktoria().sortuTiroa(ontziX, ontziY - 2, "PIXEL"));
		return berriak;
	}
}
