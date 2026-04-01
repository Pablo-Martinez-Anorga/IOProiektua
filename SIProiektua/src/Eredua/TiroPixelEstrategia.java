package Eredua;

import java.util.ArrayList;
import java.util.List;

public class TiroPixelEstrategia implements TiroEstrategia{

		@Override
	    public List<Tiroa> tiroEgin(int ontziX, int ontziY, int ontziAltuera) {
	        // Pertsona 3 gezi tiroak
	        List<Tiroa> berriak = new ArrayList<>();
	        int tiroY = ontziY - (ontziAltuera / 2) - 1;
	        berriak.add(new Tiroa(ontziX, tiroY));
	        return berriak;
    }
}
